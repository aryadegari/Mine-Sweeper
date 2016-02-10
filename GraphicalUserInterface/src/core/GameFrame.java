package core;

import data.*;
import ui.AccountInfo;
import ui.SettingsData;
import utils.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class GameFrame
        extends JFrame
        implements ActionListener {

    private final int ITEM_PLAIN = 0;    // Item types
    private final int ITEM_CHECK = 1;
    private final int ITEM_RADIO = 2;

    private int rows, cols, w, h, LRMargin = 60, UDMargin = 100, boardPW, boardPH;

    private JPanel mainPanel;
    private JPanel boardPanel;
    private JMenuBar menuBar;
    private JMenu menuGame;
    private JMenu menuHelp;
    private JMenu menuUsername;
    private JMenuItem menuGameNewGame;
    private JMenuItem menuGameStats;
    private JMenuItem menuGameOptions;
    private JMenuItem menuGameAppearance;
    private JMenuItem menuGameExit;
    private JMenuItem menuHelpViewHelp;
    private JMenuItem menuHelpAboutUs;
    private JMenuItem menuUsernameChangePass;
    private JMenuItem menuUsernameLogout;


    private SettingsData settingsData;
    private AccountInfo accountInfo;
    private GameState gameState;
    private Board board;
    private CareTaker careTaker;
    private CommandExit cmExit;
    private CommandMarkCell cmMarkCell;
    private CommandOpenCell cmOpenCell;
    private CommandRedo cmRedo;
    private CommandUndo cmUndo;
    private CommandResetGame cmResetGame;

    private MyButton[][] buttons;

    public GameFrame(AccountInfo accountInfo, SettingsData settingsData) {
//        this.rows=settingsData.gameLevel.level.rows;
//        this.cols=settingsData.gameLevel.level.cols;
        rows = 8;
        cols = 8;
        buttons = new MyButton[rows][cols];
//        w=getFrameDimension(settingsData.gameLevel).width;
//        h=getFrameDimension(settingsData.gameLevel).height;
        w = Consts.getFrameDimension(new GameLevel(new BeginnerLevel())).width;
        h = Consts.getFrameDimension(new GameLevel(new BeginnerLevel())).height;
//        board= Board.getInstance(settingsData.gameLevel);
        board = Board.getInstance(new GameLevel(new BeginnerLevel()));
        boardPW = w - LRMargin;
        boardPH = h - UDMargin;
        gameState = new GameState(Board.getInstance(settingsData.gameLevel));
        this.accountInfo = accountInfo;
        this.settingsData = settingsData;
        careTaker=new CareTaker(gameState);
        createFrame();
    }

    void createFrame() {
        setTitle("Minesweeper");
        setSize(w, h);
        setLocation(Consts.getCenterPos(Consts.SCR_WIDTH, w), Consts.getCenterPos(Consts.SCR_HEIGHT, h));
        setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(getWidth(), getHeight());
//        getContentPane().add(mainPanel);
        boardPanel = new JPanel();
        paintCells();

        menuBar = new JMenuBar();

        setJMenuBar(menuBar);

        menuUsername = new JMenu("Username");
        menuUsername.setMnemonic('u');
        menuGame = new JMenu("Game");
        menuGame.setMnemonic('g');
        setGameMenu();
        setHelpMenu();
        setUserMenu();

    }

    void win() {
        new WinFrame("You win");
        Consts.gameStatus= Consts.GameStatus.WIN;
    }

    void gameOver() {
        new WinFrame("Gameover");
        Consts.gameStatus= Consts.GameStatus.GAMEOVER;
    }

    public void paintCells() {
        boardPanel.setSize(boardPW, boardPH);
        boardPanel.setLocation(Consts.getCenterPos(w, boardPW), 25);
        boardPanel.setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new MyButton(i, j, board.getCell(i, j));
                final int a = i, b = j;
                MouseAdapter mouseAdapter = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (Consts.gameStatus==Consts.GameStatus.PLAY)
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                Consts.GameStatus gameStatus = board.openCell(buttons[a][b].row, buttons[a][b].col);
                                switch (gameStatus) {
                                    case WIN:
                                        win();
                                        break;
                                    case GAMEOVER:
                                        gameOver();
                                        break;
                                    default:
                                        break;
                                }
                            } else {
                                if (board.getCell(a, b).getState() == CellState.MARKED)
                                    board.getCell(a, b).setState(CellState.CLOSE);
                                else
                                    board.markCell(buttons[a][b].row, buttons[a][b].col);
                            }
                        cellClicked();
                    }
                };
                buttons[i][j].addMouseListener(mouseAdapter);
                boardPanel.add(buttons[i][j]);
            }
        }
        mainPanel.add(boardPanel);
        getContentPane().add(mainPanel);
    }

    void cellClicked() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board.getCell(i, j).getState() != CellState.CLOSE) {
                    String address = "";
                    if (board.getCell(i, j).getState() == CellState.OPEN) {
                        String state = board.getCell(i, j).getClass().getSimpleName();
                        switch (state) {
                            case "MineCell":
                                address = "../resources/1.png";
                                break;
                            case "EmptyCell":
                                address = "../resources/empty.png";
                                break;
                            case "NumCell":
                                address = "../resources/number_" + (((NumCell) board.getCell(i, j)).getNeighbourMines()) + ".png";
                                break;
                            default:
                                break;
                        }
                    } else {
                        address = "../resources/flag.png";
                    }
                    java.net.URL imgURL = getClass().getResource(address);
                    if (imgURL != null) {
                        buttons[i][j].setIcon(new ImageIcon(imgURL));
                    } else {
                        System.err.println("Couldn't find file: " + address);
                    }
                } else {
                    buttons[i][j].setIcon(null);
                }
            }
        }
    }

    public JMenuItem CreateMenuItem(JMenu menu, int iType, String sText,
                                    ImageIcon image, int acceleratorKey,
                                    String sToolTip) {
        // Create the item
        JMenuItem menuItem;
        menuItem = new JMenuItem();

        // Add the item test
        menuItem.setText(sText);

        // Add the optional icon
        if (image != null)
            menuItem.setIcon(image);

        // Add the accelerator key
        if (acceleratorKey > 0)
            menuItem.setMnemonic(acceleratorKey);

        // Add the optional tool tip text
        if (sToolTip != null)
            menuItem.setToolTipText(sToolTip);

        // Add an action handler to this menu item
        menuItem.addActionListener(this);

        menu.add(menuItem);

        return menuItem;
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println(event);
    }

    void setGameMenu() {


        menuBar.add(menuGame);

        menuGameNewGame = CreateMenuItem(menuGame, ITEM_PLAIN,
                "NewGame", null, 'n', null);
        menuGame.addSeparator();
        menuGameStats = CreateMenuItem(menuGame, ITEM_PLAIN,
                "Statistics", null, 's', null);
        menuGameOptions = CreateMenuItem(menuGame, ITEM_PLAIN,
                "Options", null, 'o', null);
        menuGameAppearance = CreateMenuItem(menuGame, ITEM_PLAIN,
                "Change Appearance", null, 'c', null);
        menuGame.addSeparator();
        menuGameExit = CreateMenuItem(menuGame, ITEM_PLAIN,
                "Exit", null, 'e', null);
    }

    void setHelpMenu(){
        menuHelp = new JMenu("Help");
        menuHelp.setMnemonic('h');
        menuHelpViewHelp = CreateMenuItem(menuHelp, ITEM_PLAIN,
                "View Help", null, 'a',
                "");
        menuHelpAboutUs = CreateMenuItem(menuHelp, ITEM_PLAIN,
                "About us", null, 'v',
                "");
        menuBar.add(menuHelp);
    }

    void setUserMenu(){
        menuUsernameLogout = CreateMenuItem(menuUsername, ITEM_PLAIN,
                "Change password", null, 'u',
                "");
        menuUsername.addSeparator();
        menuUsernameChangePass = CreateMenuItem(menuUsername, ITEM_PLAIN,
                "Logout", null, 'l',
                "");
        menuBar.add(menuUsername);
    }

    void setLabels() {
        JLabel timeL=new JLabel("0");
        timeL.setSize(70, 20);
        timeL.setLocation(0,0);
        timeL.setLayout(null);
        getContentPane().add(timeL);
        JLabel movesL=new JLabel("0");
        movesL.setSize(70, 20);
        movesL.setLocation(70,0);
        movesL.setLayout(null);
        getContentPane().add(movesL);
        JButton undo=new JButton();
        undo.setSize(70, 20);
        undo.setLocation(140,0);
        undo.setLayout(null);
        undo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        getContentPane().add(undo);
        JButton redo=new JButton();
        redo.setSize(70, 20);
        redo.setLocation(210,0);
        redo.setLayout(null);
        redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        getContentPane().add(redo);
    }

    public static void main(String args[]) {
        // Create an instance of the test application
        GameFrame mainFrame = new GameFrame(new AccountInfo("g", "g"), new SettingsData());
        mainFrame.setVisible(true);
    }
}

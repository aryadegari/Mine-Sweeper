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
import java.util.concurrent.TimeUnit;

class GameFrame
        extends JFrame
        implements ActionListener, Runnable {

    private final int ITEM_PLAIN = 0;    // Item types
    private final int ITEM_CHECK = 1;
    private final int ITEM_RADIO = 2;

    private int rows, cols, w, h, LRMargin = 60, UDMargin = 130, boardPW, boardPH;

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
    private JLabel timeL=new JLabel("0"), movesL=new JLabel("0");


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
        createFrame();
        careTaker=new CareTaker(gameState);
        cmMarkCell=new CommandMarkCell(gameState, careTaker);
        cmOpenCell=new CommandOpenCell(gameState, careTaker);
        cmRedo=new CommandRedo(gameState, careTaker);
        cmUndo=new CommandUndo(gameState, careTaker);
        cmResetGame=new CommandResetGame(gameState);
        setLabels();
    }

    void createFrame() {
        setTitle("Minesweeper");
        setSize(w+10, h);
        setLocation(Consts.getCenterPos(Consts.SCR_WIDTH, w), Consts.getCenterPos(Consts.SCR_HEIGHT, h));
        setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(getWidth(), Consts.FRAME_H-20);
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
        Consts.gameStatus= Consts.GameStatus.GAMEOVER;
        new WinFrame("Gameover");
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
//                                Consts.GameStatus gameStatus = board.openCell(buttons[a][b].row, buttons[a][b].col);
                                repaint();
                                cmOpenCell.setPos(buttons[a][b].row, buttons[a][b].col);
                                cmOpenCell.execute();
                                switch (Consts.gameStatus) {
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
//                                if (board.getCell(a, b).getState() == CellState.MARKED)
//                                    board.getCell(a, b).setState(CellState.CLOSE);
//                                else
//                                    board.markCell(buttons[a][b].row, buttons[a][b].col);
                                cmMarkCell.setPos(buttons[a][b].row, buttons[a][b].col);
                                cmMarkCell.execute();
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
        JMenuItem menuItem;
        menuItem = new JMenuItem();

        menuItem.setText(sText);

        if (acceleratorKey > 0)
            menuItem.setMnemonic(acceleratorKey);

        if (sToolTip != null)
            menuItem.setToolTipText(sToolTip);

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
        JLabel time=new JLabel("Time:");
        time.setSize(40, 20);
        time.setLocation(20,Consts.FRAME_H-80);
        mainPanel.add(time);
        timeL.setSize(20, 20);
        timeL.setLocation(60,Consts.FRAME_H-80);
        timeL.setLayout(null);
        mainPanel.add(timeL);
        JLabel moves=new JLabel("Moves: ");
        moves.setSize(50, 20);
        moves.setLocation(80,Consts.FRAME_H-80);
        mainPanel.add(moves);
        movesL.setSize(30, 20);
        movesL.setLocation(130,Consts.FRAME_H-80);
        mainPanel.add(movesL);
//        JButton undo=new JButton("<-");
//        undo.setSize(50, 20);
//        undo.setLocation(160,Consts.FRAME_H-80);
//        undo.setLayout(null);
//        undo.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                cmUndo.execute();
//                cellClicked();
//
//            }
//        });
//        mainPanel.add(undo);
//        JButton redo=new JButton("->");
//        redo.setSize(50, 20);
//        redo.setLocation(210,Consts.FRAME_H-80);
//        redo.setLayout(null);
//        redo.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                cmRedo.execute();
//                cellClicked();
//            }
//        });
//        mainPanel.add(redo);
    }

    public static void main(String args[]) {
        GameFrame mainFrame = new GameFrame(new AccountInfo("g", "g"), new SettingsData());
        new Thread(mainFrame).start();
        mainFrame.setVisible(true);
    }

    @Override
    public void run() {
        while (Consts.gameStatus== Consts.GameStatus.PLAY){
            timeL.setText(String.valueOf(gameState.getTimeSecs()));
            movesL.setText(String.valueOf(gameState.getNumberOfMoves()));
            gameState.increaseTimesecs();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

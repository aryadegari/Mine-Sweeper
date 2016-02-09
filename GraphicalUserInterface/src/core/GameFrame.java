package core;

import data.*;
import ui.AccountInfo;
import ui.SettingsData;
import utils.Consts;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import static utils.Consts.getFrameDimension;

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

    private MyButton[][] buttons;

    public GameFrame(AccountInfo accountInfo, SettingsData settingsData) {
//        this.rows=settingsData.gameLevel.level.rows;
//        this.cols=settingsData.gameLevel.level.cols;
        rows = 8;
        cols = 8;
        buttons = new MyButton[rows][cols];
//        w=getFrameDimension(settingsData.gameLevel).width;
//        h=getFrameDimension(settingsData.gameLevel).height;
        w = getFrameDimension(new GameLevel(new BeginnerLevel())).width;
        h = getFrameDimension(new GameLevel(new BeginnerLevel())).height;
//        board= Board.getInstance(settingsData.gameLevel);
        board = Board.getInstance(new GameLevel(new BeginnerLevel()));
        boardPW = w - LRMargin;
        boardPH = h - UDMargin;
        gameState = new GameState(Board.getInstance(settingsData.gameLevel));
        this.accountInfo = accountInfo;
        this.settingsData = settingsData;
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

        // Create the menu bar
        menuBar = new JMenuBar();

        // Set this instance as the application's menu bar
        setJMenuBar(menuBar);

        // Build the property sub-menu
        menuUsername = new JMenu("Username");
        menuUsername.setMnemonic('u');
        menuGame = new JMenu("Game");
        menuGame.setMnemonic('g');
        menuBar.add(menuGame);

        // Create property items
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


        // Create the file menu
        menuHelp = new JMenu("Help");
        menuHelp.setMnemonic('h');
        menuHelpViewHelp = CreateMenuItem(menuHelp, ITEM_PLAIN,
                "View Help", null, 'a',
                "Cut data to the clipboard");
        menuHelpAboutUs = CreateMenuItem(menuHelp, ITEM_PLAIN,
                "About us", null, 'v',
                "Cut data to the clipboard");
        menuBar.add(menuHelp);

        // Create edit menu options
        menuUsernameLogout = CreateMenuItem(menuUsername, ITEM_PLAIN,
                "Change password", null, 'u',
                "Paste data from the clipboard");
        menuUsername.addSeparator();
        menuUsernameChangePass = CreateMenuItem(menuUsername, ITEM_PLAIN,
                "Logout", null, 'l',
                "Cut data to the clipboard");
        menuBar.add(menuUsername);
    }

    void win() {
        System.out.println("win");
    }

    void gameOver() {
        System.out.println("gameOver");
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


    public static void main(String args[]) {
        // Create an instance of the test application
        GameFrame mainFrame = new GameFrame(new AccountInfo("g", "g"), new SettingsData());
        mainFrame.setVisible(true);
    }
}

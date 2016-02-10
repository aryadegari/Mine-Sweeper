package core;

import utils.Consts;
import utils.UserInterfaceConsts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by shahmohamadi on 2016-02-07.
 */
public class Game {

    public static enum GameState {
        Playing, Finished
    }

    private int width = 0;
    private int height = 0;
    JFrame frame;
    JPanel mainPanel, boardPanel;
    int rows = 9, cols = 9;

    public Game(int width, int height) {
        this.width = width;
        this.height = height;
        initializeFrame();
    }

    public Game() {
//        width = Consts.FRAME_DEFAULT_W;
//        height = Consts.FRAME_DEFAULT_H;
        initializeFrame();
    }

    void initializeFrame() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols));
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        frame = new JFrame("Minesweeper");
        frame.setSize(width, height);
        frame.setLocation(Consts.getCenterPos(Consts.SCR_WIDTH, width), Consts.getCenterPos(Consts.SCR_HEIGHT, height));
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(mainPanel);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setJMenuBar(getGameMenuBar());
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    JMenuBar getGameMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
//        menuBar.setSize(Consts.FRAME_W, Consts.MENU_NAME_H);
        menuBar.setLocation(0, 0);
        menuBar.setLayout(null);
        menuBar.add(getGameMenu());
        menuBar.add(getMyHelpMenu());
        menuBar.add(getUsernameMenu());
        return menuBar;
    }

    JMenu getGameMenu() {
        return getSimpleMenu("Game", 'g', 0);
    }

    JMenu getMyHelpMenu() {
        return getSimpleMenu("Help", 'h', 1);
    }

    JMenu getUsernameMenu() {
        return getSimpleMenu("Guest", 'g', 2);
    }

    JMenu getSimpleMenu(String name, char mnemonicChar, int position) {
        JMenu simpleMenu = new JMenu(name);
        simpleMenu.setSize(Consts.MENU_NAME_W, Consts.MENU_NAME_H);
        simpleMenu.setLocation(position * Consts.MENU_NAME_W, 0);
        simpleMenu.setLayout(null);
        simpleMenu.setMnemonic(mnemonicChar);


        JMenuItem myItem = new JMenuItem("Open");
        simpleMenu.add(myItem);
        myItem = new JMenuItem("Close");
        simpleMenu.add(myItem);
        simpleMenu.addSeparator();
        myItem = new JMenuItem("Exit");
        simpleMenu.add(myItem);


        return simpleMenu;
    }

    public static void main(String[] args) {
        new Game();
    }
}

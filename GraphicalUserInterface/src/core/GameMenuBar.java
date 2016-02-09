package core;

import core.menus.GameMenu;
import core.menus.HelpMenu;
import core.menus.UsernameMenu;
import utils.Consts;

import javax.swing.*;

/**
 * Created by shahmohamadi on 2016-02-07.
 */
public class GameMenuBar extends JMenuBar {
    public GameMenuBar(){
//        setSize(Consts.FRAME_W, Consts.MENU_NAME_H);
        setLocation(0,0);
        setLayout(null);
        add(getGameMenu());
        add(getMyHelpMenu());
        add(getUsernameMenu());
        setVisible(true);
    }

    JMenu getGameMenu(){
        return getSimpleMenu("Game", 'g', 0);
    }

    JMenu getMyHelpMenu(){
        return getSimpleMenu("Help", 'h', 1);
    }

    JMenu getUsernameMenu(){
        return getSimpleMenu("Guest", 'g', 2);
    }

    JMenu getSimpleMenu(String name, char mnemonicChar, int position){
        JMenu simpleMenu=new JMenu(name);
        simpleMenu.setSize(Consts.MENU_NAME_W, Consts.MENU_NAME_H);
        simpleMenu.setLocation(position*Consts.MENU_NAME_W,0);
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
}

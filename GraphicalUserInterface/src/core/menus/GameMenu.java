package core.menus;

//import core.GameFrame;

import javax.swing.*;

/**
 * Created by shahmohamadi on 2016-02-07.
 */
public class GameMenu extends IMenu{

    public GameMenu() {
        name="Game";
        mnemonicChar='g';
        position=0;
        JMenuItem myItem = new JMenuItem("Open");
        add(myItem);
        myItem = new JMenuItem("Close");
        add(myItem);
        addSeparator();
        myItem = new JMenuItem("Exit");
        add(myItem);
        construct();
    }
}

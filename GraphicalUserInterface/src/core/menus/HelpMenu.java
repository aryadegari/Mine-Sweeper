package core.menus;

import javax.swing.*;

/**
 * Created by shahmohamadi on 2016-02-07.
 */
public class HelpMenu extends IMenu {

    public HelpMenu() {
        name="Help";
        mnemonicChar='h';
        position=1;
        construct();
        JMenuItem myItem = new JMenuItem("Open");
        add(myItem);
        myItem = new JMenuItem("Close");
        add(myItem);
        addSeparator();
        myItem = new JMenuItem("Exit");
        add(myItem);
    }
}

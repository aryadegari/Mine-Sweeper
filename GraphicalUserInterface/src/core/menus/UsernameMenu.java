package core.menus;

import javax.swing.*;

/**
 * Created by shahmohamadi on 2016-02-07.
 */
public class UsernameMenu extends IMenu {

    public UsernameMenu(String userName){
        name = userName;
        mnemonicChar=userName.charAt(0);
        initialize();
    }
    public UsernameMenu(){
        name="Guest";
        mnemonicChar='g';
        initialize();
    }
    void initialize(){
        position=2;
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

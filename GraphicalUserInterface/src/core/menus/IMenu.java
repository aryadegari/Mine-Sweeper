package core.menus;

import utils.Consts;
import utils.UserInterfaceConsts;

import javax.swing.*;

/**
 * Created by shahmohamadi on 2016-02-07.
 */
public abstract class IMenu extends JMenu {
    String name="GHOLI";
    int position;
    char mnemonicChar;
    protected void construct(){
        setSize(Consts.MENU_NAME_W, Consts.MENU_NAME_H);
        setLocation(position* Consts.MENU_NAME_W,0);
        setLayout(null);
        setMnemonic(mnemonicChar);
    }
}

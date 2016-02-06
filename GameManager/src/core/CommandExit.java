package core;

import javax.swing.*;

/**
 * Created by hp on 13/12/2015.
 */
public class CommandExit extends CommandBase {

    JFrame frame;
    public CommandExit(JFrame frame){
        this.frame=frame;
    }
    @Override
    public void execute() {
        frame.dispose();
    }
}

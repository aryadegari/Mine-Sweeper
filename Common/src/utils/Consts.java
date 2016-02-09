package utils;

import data.GameLevel;

import java.awt.*;

/**
 * Created by hp on 12/12/2015.
 */
public class Consts {

    public static enum GameStatus{
        WIN, GAMEOVER, PLAY
    }
    public static final String SETTINGS_FILE_PATH = "settings.dat";
    public static final String LANGUAGES_BUNDLE_NAME = "languages";
    public static final String ACCOUNTS_GUEST_USER_NAME= "guest";
    public static final String ACCOUNTS_GUEST_PASSWORD= "guest";

    public static final int SCR_WIDTH= (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int SCR_HEIGHT=(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static final int MENU_NAME_W=70;
    public static final int MENU_NAME_H=20;

    public static int FRAME_W;
    public static int FRAME_H;

    static public int getCenterPos(int a, int size){
        return (int)((a-size)/2);
    }

    static public Dimension getFrameDimension(GameLevel gameLevel){
        FRAME_W=gameLevel.level.cols*20+100;
        FRAME_H=gameLevel.level.rows*20+150;
        return new Dimension(FRAME_W, FRAME_H);
    }
}

package ui;

/**
 * Created by hp on 13/12/2015.
 */
public class GameManager {

    private static GameManager ourInstance = new GameManager();

    public static GameManager getInstance() {
        return ourInstance;
    }

    private GameManager() {
    }
}

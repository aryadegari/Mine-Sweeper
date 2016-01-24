package ui;

import data.GameState;

/**
 * Created by hp on 09/12/2015.
 */
public class AccountInfo {

    public String username;
    public String password;

    public AccountInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void updateStats(boolean win) {

    }

    public Statistic getStats() {
        return null;
    }

    public GameState loadGameState() {
        return null;
    }

    public void resetStats() {

    }
}

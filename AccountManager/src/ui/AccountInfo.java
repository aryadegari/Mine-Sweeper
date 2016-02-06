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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountInfo that = (AccountInfo) o;

        return username != null ? username.equals(that.username) : that.username == null;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "username='" + username +
                '}';
    }
}

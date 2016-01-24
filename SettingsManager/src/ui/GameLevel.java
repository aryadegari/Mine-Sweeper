package ui;

/**
 * Created by hp on 12/12/2015.
 */
public class GameLevel {

    public int rows;
    public int cols;
    public int mines;

    public GameLevel(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    public static GameLevel beginner() {
        return new GameLevel(10, 10, 10);
    }

    public static GameLevel intermediate() {
        return new GameLevel(16, 16, 40);
    }

    public static GameLevel expert() {
        return new GameLevel(22, 22, 80);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameLevel gameLevel = (GameLevel) o;

        if (rows != gameLevel.rows) return false;
        if (cols != gameLevel.cols) return false;
        return mines == gameLevel.mines;

    }
}

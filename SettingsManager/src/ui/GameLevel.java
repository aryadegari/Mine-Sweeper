package ui;

/**
 * Created by hp on 12/12/2015.
 */
public class GameLevel {

    public LevelName levelName;
    public int rows;
    public int cols;
    public int mines;

    public GameLevel(LevelName levelName) {
        switch (levelName) {
            case Beginner:
                this.init(levelName, 10, 10, 10);
                break;
            case Intermediate:
                this.init(levelName, 16, 16, 40);
                break;
            case Expert:
                this.init(levelName, 22, 22, 80);
                break;
        }
    }

    private void init(LevelName levelName, int rows, int cols, int mines) {
        this.levelName = levelName;
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
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

    enum LevelName {
        Beginner, Intermediate, Expert
    }
}

package data;

/**
 * Created by hp on 12/12/2015.
 */
public class GameLevel {

    public LevelBase level;

    public GameLevel(LevelBase level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameLevel gameLevel = (GameLevel) o;

        if (level.rows != gameLevel.level.rows) return false;
        if (level.cols != gameLevel.level.cols) return false;
        return level.mines == gameLevel.level.mines;

    }

}

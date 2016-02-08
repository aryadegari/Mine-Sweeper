package data;

/**
 * Created by hp on 31/01/2016.
 */
public abstract class LevelBase {

    private String name = getClass().getSimpleName();

    public int rows;
    public int cols;
    public int mines;

    public LevelBase() {
    }

    @Override
    public String toString() {
        return "LevelBase{" +
                "rows=" + rows +
                ", cols=" + cols +
                ", mines=" + mines +
                '}';
    }
}

package data;

/**
 * Created by hp on 31/01/2016.
 */
public class BeginnerLevel extends LevelBase {

    public BeginnerLevel() {
        this.rows = 8;
        this.cols = 8;
        this.mines = 10;
    }

    @Override
    public String toString() {
        return "BeginnerLevel{} " + super.toString();
    }
}

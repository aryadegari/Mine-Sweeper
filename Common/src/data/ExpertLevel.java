package data;

/**
 * Created by hp on 31/01/2016.
 */
public class ExpertLevel extends LevelBase {

    public ExpertLevel() {
        this.rows = 16;
        this.cols = 31;
        this.mines = 99;
    }

    @Override
    public String toString() {
        return "ExpertLevel{} " + super.toString();
    }
}

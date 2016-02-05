package data;

/**
 * Created by hp on 13/12/2015.
 */
public class NumCell extends CellBase {

    int neighbourMines = 0;

    public void addNeighbourMine() {
        neighbourMines++;
    }

    public int getNeighbourMines() {
        return neighbourMines;
    }
//    @Override
//    public void open(CellOpener opener) {
//        setState(CellState.OPEN);
//        opener.open(this);
//    }
}

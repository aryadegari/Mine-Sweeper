package data;

/**
 * Created by hp on 13/12/2015.
 */
public class NumCell extends CellBase {
    String name = NumCell.class.getSimpleName();
    int neighbourMines = 0;
    public NumCell(){

    }

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

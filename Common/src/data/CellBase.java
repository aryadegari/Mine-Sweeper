package data;

import java.util.ArrayList;

/**
 * Created by hp on 13/12/2015.
 */
public abstract class CellBase {

    ArrayList<CellBase> relatedCells;
    CellState state = CellState.CLOSE;


    public void addRelatedCell(CellBase cell) {
        relatedCells.add(cell);
    }

    public ArrayList<CellBase> getRelatedCell() {
        return relatedCells;
    }
//    abstract public void open(CellOpener opener);

    public void open() {
        for (CellBase relatedCell : getRelatedCell())
            relatedCell.open();
    }

    public void mark() {
        setState(CellState.MARKED);
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }
}

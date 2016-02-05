package data;

import java.util.ArrayList;

/**
 * Created by hp on 13/12/2015.
 */
public abstract class CellBase {

    CellState state = CellState.CLOSE;

    public CellBase() {
    }

//    abstract public void open(CellOpener opener);

    public void open() {
//        if (state == CellState.OPEN)
//            return;
        state = CellState.OPEN;
//        for (CellBase relatedCell : relatedCells)
//            relatedCell.open();
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

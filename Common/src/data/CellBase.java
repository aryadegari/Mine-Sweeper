package data;

import java.util.ArrayList;

/**
 * Created by hp on 13/12/2015.
 */
public abstract class CellBase {

    public ArrayList<CellBase> relatedCells;
    public CellState state;

    abstract public void open();
}

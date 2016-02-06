package data;

/**
 * Created by hp on 13/12/2015.
 */
public abstract class CellBase {
    String name = CellBase.class.getSimpleName();
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellBase cellBase = (CellBase) o;

        if (name != null ? !name.equals(cellBase.name) : cellBase.name != null) return false;
        return state == cellBase.state;

    }
}

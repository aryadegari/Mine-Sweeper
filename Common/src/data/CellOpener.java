package data;

/**
 * Created by hp on 13/12/2015.
 */
public class CellOpener implements ICellOpener{


    @Override
    public void open(EmptyCell cell) {
        cell.open();
    }

    @Override
    public void open(MineCell cell) {
        cell.open();
    }

    @Override
    public void open(NumCell cell) {
        cell.open();
    }
}

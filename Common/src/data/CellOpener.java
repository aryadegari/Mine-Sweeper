package data;

/**
 * Created by hp on 13/12/2015.
 */
public class CellOpener implements ICellOpener {


    @Override
    public void open(EmptyCell cell) {
        for (CellBase relatedCell : cell.getRelatedCell())
            relatedCell.open();
    }

    @Override
    public void open(MineCell cell) {

    }

    @Override
    public void open(NumCell cell) {

    }
}

package data;

/**
 * Created by hp on 13/12/2015.
 */
public class EmptyCell extends CellBase {

    String name = EmptyCell.class.getSimpleName();

    public EmptyCell() {

    }
//    @Override
//    public void open(CellOpener opener) {
//        setState(CellState.OPEN);
//        opener.open(this);
//    }
}

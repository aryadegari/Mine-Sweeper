package data;

/**
 * Created by hp on 13/12/2015.
 */
public interface ICellOpener {
    public void open(EmptyCell cell);

    public void open(MineCell cell);

    public void open(NumCell cell);
}

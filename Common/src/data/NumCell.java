package data;

/**
 * Created by hp on 13/12/2015.
 */
public class NumCell extends CellBase {
    int neighbourMines = 0;

    public NumCell() {
        name = NumCell.class.getSimpleName();
    }

    public void addNeighbourMine() {
        neighbourMines++;
    }

    public int getNeighbourMines() {
        return neighbourMines;
    }

    @Override
    public String toString() {
        return "NumCell{" +
                "neighbourMines=" + neighbourMines +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        NumCell numCell = (NumCell) o;

        return neighbourMines == numCell.neighbourMines;

    }
}

package data;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by hp on 13/12/2015.
 */
public class Board {

    ArrayList<ArrayList<CellBase>> cells = new ArrayList<>();
    int rows = 0, cols = 0, mines = 0;
    ArrayList<Point> minesPos = new ArrayList<>();

    private static Board ourInstance;

    public static Board getInstance(GameLevel gameLevel) {
        if (ourInstance != null)
            return ourInstance;
        ourInstance = new Board(gameLevel);
        return ourInstance;
    }

    private Board(GameLevel gameLevel) {
        rows = gameLevel.level.rows;
        cols = gameLevel.level.cols;
        mines = gameLevel.level.mines;
        resetBoard();
    }

    void init() {
        for (int i = 0; i < mines; i++) {
            int randRow = (int) (Math.random() * rows), randCol = (int) (Math.random() * cols);
            if (getCell(randRow, randCol) instanceof MineCell)
                i--;
            else {
                minesPos.add(new Point(randRow, randCol));
                setMine(randRow, randCol);
            }
        }
    }

    public void resetBoard() {
        cells.clear();
        for (int i = 0; i < rows; i++) {
            cells.add(new ArrayList<CellBase>());
            for (int j = 0; j < cols; j++) {
                cells.get(i).add(new EmptyCell());
            }
        }
        init();
    }

    void setMine(int row, int col) {
        cells.get(row).set(col, new MineCell());
        increaseNeighbourMines(row, col);
    }

    void increaseNeighbourMines(int row, int col) {
        for (Point p : neighbours(row, col)) {
            if (!(getCell(p.x, p.y) instanceof MineCell)) {
                if (getCell(p.x, p.y) instanceof EmptyCell)
                    cells.get(p.x).set(p.y, new NumCell());
                ((NumCell) getCell(p.x, p.y)).addNeighbourMine();
            }
        }
    }

    ArrayList<Point> neighbours(int row, int col) {
        ArrayList<Point> neighbours = new ArrayList<>();
        for (int i = row - 1; i <= row + 1; i++)
            for (int j = col - 1; j <= col + 1; j++)
                if (!(i == row && j == col))
                    if (i >= 0 && i < rows && j >= 0 && j < cols)
                        neighbours.add(new Point(i, j));
        return neighbours;
    }

    public CellBase getCell(int i, int j) {
        return cells.get(i).get(j);
    }

    public void openCell(int row, int col) {
        if (getCell(row, col).getState() == CellState.OPEN)
            return;
        getCell(row, col).open();
        openRelatedCells(row, col);
    }

    public void markCell(int row, int col) {
        getCell(row, col).mark();
    }

    void openRelatedCells(int row, int col) {
        if (getCell(row, col) instanceof MineCell)
            for (int k = 0; k < minesPos.size(); k++) {
                openCell(minesPos.get(k).x, minesPos.get(k).y);
            }
        else if (getCell(row, col) instanceof EmptyCell) {
            for (int k = 0; k < neighbours(row, col).size(); k++) {
                Point neighbour = neighbours(row, col).get(k);
                if ((Math.abs(neighbour.x - row) + Math.abs(neighbour.y - col)) < 2)
                    if (getCell(neighbour.x, neighbour.y) instanceof EmptyCell) {
                        openCell(neighbour.x, neighbour.y);
                    }
            }
        }
    }

}

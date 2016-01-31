package data;

import java.util.ArrayList;

/**
 * Created by hp on 13/12/2015.
 */
public class Board {

    CellBase[][] cells;
    int rows, cols, mines;

    public Board(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
        resetCells();
    }

    void init() {
        for (int i = 0; i < mines; i++) {
            int randRow = (int) (Math.random() * rows), randCol = (int) (Math.random() * cols);
            if (cells[randRow][randCol] instanceof MineCell)
                i--;
            else {
                cells[randRow][randCol] = new MineCell();
            }
        }
    }

    public void resetCells() {
        cells = new EmptyCell[rows][cols];
        init();
    }

    void setMine(int row, int col) {
        cells[row][col] = new MineCell();
        increaseNeighbourMines(row, col);
    }

    void increaseNeighbourMines(int row, int col) {
        for (CellBase cell : neighbour(row, col)) {
            if (cell instanceof EmptyCell)
                cell = new NumCell();
            ((NumCell) cell).increaseNeighbourMines();
        }
    }

    ArrayList<CellBase> neighbour(int row, int col) {
        ArrayList<CellBase> neighbours = new ArrayList<CellBase>();
        for (int i = row - 1; i <= row + 1; i++)
            for (int j = col - 1; j <= col + 1; j++)
                if (!(i == row && j == col))
                    if (i >= 1 && i <= rows && j >= 1 && j <= rows)
                        neighbours.add(cells[i][j]);
        return neighbours;
    }
}

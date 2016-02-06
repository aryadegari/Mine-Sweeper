package test;

import com.google.gson.Gson;
import data.*;
import org.junit.Test;

/**
 * Created by shahmohamadi on 2016-02-05.
 */
public class CommonTest {
    @Test
    public void gameStateTest() {
        Board board = Board.getInstance(new GameLevel(new IntermediateLevel()));
        System.out.println("INITIALIZED BOARD:");
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (board.getCell(i, j) instanceof EmptyCell)
                    System.out.print("-");
                else if (board.getCell(i, j) instanceof MineCell)
                    System.out.print("X");
                else
                    System.out.print(((NumCell) board.getCell(i, j)).getNeighbourMines());
            }
            System.out.println();
        }
        System.out.println(new Gson().toJson(board));
        System.out.println("OPEN (0,0)");
        board.openCell(0, 0);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (board.getCell(i, j).getState() == CellState.OPEN)
                    System.out.print("o");
                else if (board.getCell(i, j) instanceof EmptyCell)
                    System.out.print("-");
                else if (board.getCell(i, j) instanceof MineCell)
                    System.out.print("X");
                else
                    System.out.print(((NumCell) board.getCell(i, j)).getNeighbourMines());
            }
            System.out.println();
        }
    }
}

package test;

import com.google.gson.Gson;
import core.*;
import data.*;
import org.junit.Test;

/**
 * Created by shahmohamadi on 2016-02-06.
 */
public class gameManagerTest {
    @Test
    public void commandOpenCell_AND_UndoTest() {
        Board board = Board.getInstance(new GameLevel(new IntermediateLevel()));
        GameState gameState = new GameState(board);
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
        System.out.println(new Gson().toJson(gameState));
        CareTaker careTaker = new CareTaker(gameState);
        CommandOpenCell openCell = new CommandOpenCell(gameState, careTaker);
        openCell.setPos(0, 0);
        openCell.execute();
        openCell.execute();
        System.out.println(careTaker.undoStack.size());
        System.out.println(careTaker.redoStack.size());
        System.out.println(new Gson().toJson(gameState));
//        CommandUndo undo = new CommandUndo(gameState, careTaker);
//        undo.execute();
//        System.out.println(new Gson().toJson(gameState));
//        CommandRedo redo = new CommandRedo(gameState, careTaker);
//        redo.execute();
//        System.out.println(new Gson().toJson(gameState));
        CommandResetGame reset = new CommandResetGame(gameState);
        reset.execute();
        System.out.println(new Gson().toJson(gameState));
        System.out.println(careTaker.undoStack.size());
        System.out.println(careTaker.redoStack.size());

    }
}

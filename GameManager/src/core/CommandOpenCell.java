package core;

import data.Board;
import data.CellState;
import data.GameState;
import data.GameStateMemento;
import utils.Consts;

/**
 * Created by shahmohamadi on 2016-02-05.
 */
public class CommandOpenCell extends CommandBase {

    GameState gameState;
    int row = -1, col = -1;
    CareTaker careTaker;

    public CommandOpenCell(GameState gameState, CareTaker careTaker) {
        this.gameState = gameState;
        this.careTaker = careTaker;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        if (row != -1 && col != -1) {
            if (gameState.getBoard().getCell(row, col).getState() == CellState.CLOSE) {
                Consts.gameStatus=gameState.getBoard().openCell(row, col);
                gameState.increaseMoves();
                careTaker.add(new GameStateMemento(gameState));
            }
        } else
            System.err.println("Please set cursor position!!!");
    }
}

package core;

import data.CellState;
import data.GameState;
import data.GameStateMemento;

/**
 * Created by shahmohamadi on 2016-02-05.
 */
public class CommandMarkCell extends CommandBase {
    GameState gameState;
    CareTaker careTaker;
    int row = -1, col = -1;

    public CommandMarkCell(GameState gameState, CareTaker careTaker) {
        this.careTaker = careTaker;
        this.gameState = gameState;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public void execute() {
        if (row != -1 && col != -1) {
            if (gameState.getBoard().getCell(row, col).getState() != CellState.OPEN) {
                careTaker.add(new GameStateMemento(gameState));
                if (gameState.getBoard().getCell(row, col).getState() == CellState.CLOSE)
                    gameState.getBoard().markCell(row, col);
                else
                    gameState.getBoard().getCell(row, col).setState(CellState.CLOSE);
            }
        } else
            System.err.println("Please set cursor position!!!");
    }
}

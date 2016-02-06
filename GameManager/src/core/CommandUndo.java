package core;

import com.google.gson.Gson;
import data.GameState;

/**
 * Created by hp on 13/12/2015.
 */
public class CommandUndo extends CommandBase {

    GameState gameState;
    CareTaker careTaker;

    public CommandUndo(GameState gameState, CareTaker careTaker) {
        this.careTaker = careTaker;
        this.gameState = gameState;
    }

    @Override
    public void execute() {
        gameState.setGameState((careTaker.undo()).getGameState());
    }
}

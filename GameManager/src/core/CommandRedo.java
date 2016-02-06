package core;

import data.GameState;

/**
 * Created by hp on 13/12/2015.
 */
public class CommandRedo extends CommandBase {

    GameState gameState;
    CareTaker careTaker;

    public CommandRedo(GameState gameState, CareTaker careTaker) {
        this.careTaker = careTaker;
        this.gameState = gameState;
    }

    @Override
    public void execute() {
        gameState.setGameState(careTaker.redo().getGameState());
    }
}

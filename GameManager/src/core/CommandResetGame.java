package core;

import data.Board;
import data.GameState;

/**
 * Created by hp on 13/12/2015.
 */
public class CommandResetGame extends CommandBase {

    GameState gameState;

    public CommandResetGame(GameState game) {
        this.gameState=game;
    }

    @Override
    public void execute() {
        gameState.resetGame();
    }
}

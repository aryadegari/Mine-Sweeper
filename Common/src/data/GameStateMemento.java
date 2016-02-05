package data;

import com.google.gson.Gson;

/**
 * Created by hp on 13/12/2015.
 */
public class GameStateMemento {

    private String state;

    public GameStateMemento(GameState state) {
        this.state = new Gson().toJson(state);
    }

    public GameState getState() {
        return new Gson().fromJson(state, GameState.class);
    }
}

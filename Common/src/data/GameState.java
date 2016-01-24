package data;

/**
 * Created by hp on 09/12/2015.
 */
public class GameState {

    public Board board;
    public int moves;
    public int timeSecs;

    public GameState(Board board, int moves, int timeSecs) {
        this.board = board;
        this.moves = moves;
        this.timeSecs = timeSecs;
    }

    public static GameState fromMemento(GameStateMemento memento) {
        return null;
    }

    public GameStateMemento toMemento() {
        return null;
    }
}

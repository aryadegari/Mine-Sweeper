package data;

/**
 * Created by hp on 09/12/2015.
 */
public class GameState {

    public Board board;
    int moves = 0;
    int timeSecs = 0;

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

    public void resetGame() {
        moves = 0;
        timeSecs = 0;
        board.resetCells();
    }

    public void increaseMoves() {
        moves++;
    }

    public void decreaseMoves() {
        moves--;
    }

    public void increaseTimesecs() {
        timeSecs++;
    }

    public int getTimeSecs() {
        return timeSecs;
    }

    public int getNumberOfMoves() {
        return moves;
    }
}

package data;

/**
 * Created by hp on 09/12/2015.
 */
public class GameState {

    Board board;
    int moves = 0;
    int timeSecs = 0;

    public GameState() {
    }

    public GameState(Board board, int moves, int timeSecs) {
        this.board = board;
        this.moves = moves;
        this.timeSecs = timeSecs;
    }

    public GameState(Board board) {
        this.board = board;
    }

    public static GameState getDefault() {
        return new GameState(Board.getInstance(new GameLevel(new BeginnerLevel())));
    }

    public static GameState fromMemento(GameStateMemento memento) {
        return memento.getGameState();
    }

    public GameStateMemento toMemento() {
        return new GameStateMemento(this);
    }

    public void resetGame() {
        moves = 0;
        timeSecs = 0;
        board.resetBoard();
    }

    public Board getBoard() {
        return board;
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

    public void setGameState(GameState gameState) {
        this.board = gameState.board;
        this.moves = gameState.moves;
        this.timeSecs = gameState.timeSecs;
    }
}

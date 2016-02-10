package core;

import data.GameState;
import data.GameStateMemento;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by shahmohamadi on 2016-02-05.
 */
public class CareTaker {

    public Stack<GameStateMemento> undoStack = new Stack<>();
    public Stack<GameStateMemento> redoStack = new Stack<>();


    public CareTaker(GameState gameState) {
        add(new GameStateMemento(gameState));
    }

    public void add(GameStateMemento memento) {

        undoStack.push(memento);
    }


    public GameStateMemento redo() {
        if (redoStack.size() > 0)
            undoStack.push(redoStack.pop());
        return undoStack.peek();
    }

    public GameStateMemento undo() {
        if (undoStack.size() > 1)
            redoStack.push(undoStack.pop());
        return undoStack.peek();
    }
}

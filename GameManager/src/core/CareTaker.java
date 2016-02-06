package core;

import data.GameState;
import data.GameStateMemento;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by shahmohamadi on 2016-02-05.
 */
public class CareTaker {
//    private ArrayList<GameStateMemento> mementoList = new ArrayList<>();

    public Stack<GameStateMemento> undoStack = new Stack<>();
    public Stack<GameStateMemento> redoStack = new Stack<>();


    public CareTaker(GameState gameState){
       add(new GameStateMemento(gameState));
    }
    public void add(GameStateMemento memento) {

//        mementoList.add(memento);
        undoStack.push(memento);
    }

//    public GameStateMemento get(int index) {
//        return mementoList.get(index);
//    }

    public GameStateMemento redo() {
        undoStack.push(redoStack.pop());
        return undoStack.peek();
    }

    public GameStateMemento undo() {
        redoStack.push(undoStack.pop());
        return undoStack.peek();
    }
}

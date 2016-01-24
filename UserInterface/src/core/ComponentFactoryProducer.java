package core;

import core.buttons.ButtonFactory;
import core.cells.CellFactory;
import core.numbers.NumberFactory;

/**
 * Created by hp on 12/12/2015.
 */
public class ComponentFactoryProducer {

    public static ComponentsAbstractFactory getFactory(ComponentFactory factory) {
        switch (factory) {
            case Button:
                return new ButtonFactory();
            case Cell:
                return new CellFactory();
            case Number:
                return new NumberFactory();
            default:
                throw new UnsupportedOperationException("Factory: '" + factory + "' is not supported.");
        }
    }

    public enum ComponentFactory {Button, Cell, Number}
}

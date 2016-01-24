package ui;

import core.ComponentFactoryProducer;
import core.ComponentsAbstractFactory;
import core.buttons.IButton;
import core.cells.ICell;
import core.numbers.INumber;

/**
 * Created by hp on 12/12/2015.
 */
public class UserInterface {

    SettingsData.Theme theme;

    public UserInterface(SettingsData.Theme theme) {
        this.theme = theme;
    }

    public void draw() {
        ComponentsAbstractFactory buttonFactory = ComponentFactoryProducer.getFactory(ComponentFactoryProducer.ComponentFactory.Button);
        IButton button = buttonFactory.getButton(theme);
        ComponentsAbstractFactory cellFactory = ComponentFactoryProducer.getFactory(ComponentFactoryProducer.ComponentFactory.Cell);
        ICell cell = cellFactory.getCell(theme);
        ComponentsAbstractFactory numberFactory = ComponentFactoryProducer.getFactory(ComponentFactoryProducer.ComponentFactory.Number);
        INumber number = numberFactory.getNumber(theme);
    }
}

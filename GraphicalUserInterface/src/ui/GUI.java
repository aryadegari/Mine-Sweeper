package ui;

import core.ComponentFactoryProducer;
import core.ComponentsAbstractFactory;
import core.Game;
import core.buttons.IButton;
import core.cells.ICell;
import core.numbers.INumber;

/**
 * Created by hp on 12/12/2015.
 */
public class GUI implements Runnable{

    SettingsData.Theme theme;

    public GUI(SettingsData.Theme theme) {
        this.theme = theme;
        Game gameFrame=new Game();
    }
    public GUI(){
        theme= SettingsData.Theme.Classic;
        Game gameFrame=new Game();
    }

    public void draw() {
        ComponentsAbstractFactory buttonFactory = ComponentFactoryProducer.getFactory(ComponentFactoryProducer.ComponentFactory.Button);
        IButton button = buttonFactory.getButton(theme);
        ComponentsAbstractFactory cellFactory = ComponentFactoryProducer.getFactory(ComponentFactoryProducer.ComponentFactory.Cell);
        ICell cell = cellFactory.getCell(theme);
        ComponentsAbstractFactory numberFactory = ComponentFactoryProducer.getFactory(ComponentFactoryProducer.ComponentFactory.Number);
        INumber number = numberFactory.getNumber(theme);
    }

    @Override
    public void run() {

        draw();
    }

    public static void main(String[] args) {
        new Thread(new GUI()).start();
    }


}

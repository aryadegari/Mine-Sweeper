package core.buttons;

import core.ComponentsAbstractFactory;
import core.cells.ICell;
import core.numbers.INumber;
import ui.SettingsData;

/**
 * Created by hp on 12/12/2015.
 */
public class ButtonFactory extends ComponentsAbstractFactory {


    @Override
    public IButton getButton(SettingsData.Theme theme) {
        switch (theme) {
            case Modern:
                return new ButtonModern();
            case Classic:
                return new ButtonClassic();
            case Cartoon:
                return new ButtonCartoon();
            default:
                throw new UnsupportedOperationException("Theme : '" + theme + "' is not supported.");
        }
    }

    @Override
    public ICell getCell(SettingsData.Theme theme) {
        return null;
    }

    @Override
    public INumber getNumber(SettingsData.Theme theme) {
        return null;
    }
}

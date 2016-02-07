package core.numbers;

import core.ComponentsAbstractFactory;
import core.buttons.IButton;
import core.cells.ICell;
import ui.SettingsData;

/**
 * Created by hp on 12/12/2015.
 */
public class NumberFactory extends ComponentsAbstractFactory {


    @Override
    public IButton getButton(SettingsData.Theme theme) {
        return null;
    }

    @Override
    public ICell getCell(SettingsData.Theme theme) {
        return null;
    }

    @Override
    public INumber getNumber(SettingsData.Theme theme) {
        switch (theme) {
            case Modern:
                return new NumberModern();
            case Classic:
                return new NumberClassic();
            case Cartoon:
                return new NumberCartoon();
            default:
                throw new UnsupportedOperationException("Theme : '" + theme + "' is not supported.");
        }
    }
}

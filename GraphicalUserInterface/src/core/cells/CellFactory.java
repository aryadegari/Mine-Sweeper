package core.cells;

import core.ComponentsAbstractFactory;
import core.buttons.IButton;
import core.numbers.INumber;
import ui.SettingsData;

/**
 * Created by hp on 12/12/2015.
 */
public class CellFactory extends ComponentsAbstractFactory {


    @Override
    public IButton getButton(SettingsData.Theme theme) {
        return null;
    }

    @Override
    public ICell getCell(SettingsData.Theme theme) {
        switch (theme) {
            case Modern:
                return new CellModern();
            case Classic:
                return new CellClassic();
            case Cartoon:
                return new CellCartoon();
            default:
                throw new UnsupportedOperationException("Theme : '" + theme + "' is not supported.");
        }
    }

    @Override
    public INumber getNumber(SettingsData.Theme theme) {
        return null;
    }
}

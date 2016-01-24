package core;

import core.buttons.IButton;
import core.cells.ICell;
import core.numbers.INumber;
import ui.SettingsData;

/**
 * Created by hp on 12/12/2015.
 */
public abstract class ComponentsAbstractFactory {

    public abstract IButton getButton(SettingsData.Theme theme);

    public abstract ICell getCell(SettingsData.Theme theme);

    public abstract INumber getNumber(SettingsData.Theme theme);
}

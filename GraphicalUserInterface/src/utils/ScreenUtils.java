package utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by hp on 07/02/2016.
 */
public final class ScreenUtils {

    public static void setCenterLocation(Window frame) {
        int width = frame.getWidth();
        int height = frame.getHeight();
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - width) / 2;
        int y = (size.height - height) / 2;
        frame.setLocation(x, y);
    }
}

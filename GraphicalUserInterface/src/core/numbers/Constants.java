package core.numbers;

import java.awt.*;

/**
 * Created by shahmohamadi on 2016-02-07.
 */
public interface Constants {
    final int SCR_WIDTH= (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    final int SCR_HEIGHT=(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    int getX(int size);
    int getY(int size);
}

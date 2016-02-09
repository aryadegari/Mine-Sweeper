package core;

import data.Board;
import data.CellBase;
import data.CellState;
import data.NumCell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by shahmohamadi on 2016-02-09.
 */
public class MyButton extends JButton {
    public int row, col;
    private Board board;

    public MyButton(int row, int col, CellBase cellBase) {
        this.row = row;
        this.col = col;
        setMargin(new Insets(0, 0, 0, 0));
        setFocusable(false);
    }
}

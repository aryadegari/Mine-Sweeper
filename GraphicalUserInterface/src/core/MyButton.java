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
        if (cellBase.getState() != CellState.CLOSE) {
            String address = "";
            if (cellBase.getState() == CellState.OPEN) {
                setEnabled(false);
                String state = cellBase.getClass().getSimpleName();
                switch (state) {
                    case "MineCell":
                        address = "../resources/1.png";
                        break;
                    case "EmptyCell":
                        setEnabled(false);
                        break;
                    case "NumCell":
                        address = "../resources/number_"+(((NumCell)cellBase).getNeighbourMines())+".png";
                        break;
                    default:
                        break;
                }
            } else {
                    address="../resources/flag.png";
            }
            java.net.URL imgURL = getClass().getResource(address);
            if (imgURL != null) {
                setIcon(new ImageIcon(imgURL));
            } else {
                System.err.println("Couldn't find file: " + address);
            }
        }
//        setText("g");
    }
}

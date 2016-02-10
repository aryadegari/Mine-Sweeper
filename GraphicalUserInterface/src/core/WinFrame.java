package core;


import utils.Consts;
import utils.UserInterfaceConsts;

import javax.swing.*;

/**
 * Created by shahmohamadi on 2016-02-09.
 */
public class WinFrame extends JFrame {
    public WinFrame(String text){
        setSize(200, 300);
        setLocation(Consts.getCenterPos(Consts.SCR_WIDTH,200), Consts.getCenterPos(Consts.SCR_HEIGHT, 300));
        setLayout(null);
        setVisible(true);
        JLabel uWin=new JLabel(text+"!");
        uWin.setSize(100,30);
        uWin.setLocation(70, 10);
        add(uWin);
//        java.net.URL imgURL = getClass().getResource("../resources/"+text+".png");
//        if (imgURL != null) {
//            img.setIcon(new ImageIcon(imgURL));
//        } else {
//            System.err.println("Couldn't find file: " + "../resources/"+text+".png");
//        }
        JLabel img=new JLabel(new ImageIcon("../resources/"+text+".png"));
        img.setSize(50,50);
        img.setLocation(50, 50);
        add(img);
        setResizable(false);
        JButton restart=new JButton("Restart");
        restart.setSize(80, 25);
        restart.setLocation(55, 160);
        getContentPane().add(restart);
        JButton exit=new JButton("Exit");
        exit.setSize(80, 25);
        exit.setLocation(55, 190);
        getContentPane().add(exit);
    }
    public static void main(String[] arg0){
        new WinFrame("You win");
    }
}

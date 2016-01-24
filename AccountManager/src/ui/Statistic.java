package ui;

/**
 * Created by hp on 09/12/2015.
 */
public class Statistic {

    public float winPercent;
    public int winNum;
    public int playNum;

    public Statistic(float winPercent, int winNum, int playNum) {
        this.winPercent = winPercent;
        this.winNum = winNum;
        this.playNum = playNum;
    }

    public static Statistic getDefault() {
        return new Statistic(0, 0, 0);
    }
}

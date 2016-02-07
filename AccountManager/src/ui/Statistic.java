package ui;

/**
 * Created by hp on 09/12/2015.
 */
public class Statistic implements Comparable<Statistic> {

    public int winNum = 0;
    public int playNum = 0;
    public int winPercent = 0;

    public Statistic(int winNum, int playNum, int winPercent) {
        this.winNum = winNum;
        this.playNum = playNum;
        this.winPercent = winPercent;
    }

    public static Statistic getDefault() {
        return new Statistic(0, 0, 0);
    }

    public void update(boolean win) {
        if (win) {
            winNum++;
        }
        playNum++;
        winPercent = (int) ((winNum / (float) playNum) * 100);
    }

    public void reset() {
        Statistic defaultStatistic = getDefault();
        winNum = defaultStatistic.winNum;
        playNum = defaultStatistic.playNum;
        winPercent = defaultStatistic.winPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistic statistic = (Statistic) o;
        if (winNum != statistic.winNum) return false;
        return playNum == statistic.playNum;

    }


    @Override
    public int compareTo(Statistic o) {
        if (winNum == o.winNum) {
            if (winPercent == o.winPercent) return 0;
            return winPercent > o.winPercent ? -1 : 1;
        }
        return winNum > o.winNum ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "winNum=" + winNum +
                ", playNum=" + playNum +
                ", winPercent=" + winPercent +
                '}';
    }
}

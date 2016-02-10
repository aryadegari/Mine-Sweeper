package ui;

import utils.ScreenUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by hp on 08/02/2016.
 */
public class Statistics {
    private JPanel panel1;
    private JTable userStatsTable;
    private JButton resetMyStatisticsButton;
    private JLabel usernameLabel;
    private JTable allStatsTable;

    public Statistics() {
        AccountManager accountManager = AccountManager.getInstance();

        //TODO: delete next line for release
        setAccountToGuest(accountManager);

        AccountInfo account = accountManager.getAccountInfo();
        usernameLabel.setText(account.username);

        initUserStatsTable();
        initAllStatsTable();

        resetMyStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountManager accountManager = AccountManager.getInstance();
                accountManager.resetStats();
//                ((DefaultTableModel) userStatsTable.getModel()).fireTableDataChanged();
                initUserStatsTable();
                initAllStatsTable();
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Statistics");
        frame.setContentPane(new Statistics().panel1);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        ScreenUtils.setCenterLocation(frame);
        frame.setVisible(true);
    }

    private void initAllStatsTable() {
        allStatsTable.setModel(new AllStatisticsTableModel());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        allStatsTable.setDefaultRenderer(Object.class, centerRenderer);
        allStatsTable.setFillsViewportHeight(true);
    }

    private void initUserStatsTable() {
        userStatsTable.setModel(new UserStatisticsTableModel());
        userStatsTable.setRowHeight(UserStatisticsTableUI.ROW_HEIGHT);
        userStatsTable.setRowMargin(UserStatisticsTableUI.ROW_MARGIN);

        for (int i = 0; i < UserStatisticsTableUI.COLUMNS_WIDTH.length; i++) {
            userStatsTable.getColumnModel().getColumn(i).setPreferredWidth(UserStatisticsTableUI.COLUMNS_WIDTH[i]);
        }
    }

    private void setAccountToGuest(AccountManager accountManager) {
        accountManager.setAccountInfo(new AccountInfo("username19", "username19"));
    }

    private static class UserStatisticsTableUI {
        public static final int ROW_HEIGHT = 40;
        public static final int ROW_MARGIN = 5;
        public static final int[] COLUMNS_WIDTH = {150};
    }

    private class UserStatisticsTableModel extends DefaultTableModel {

        private final int ROWS_NUM = 3;
        private final int COLS_NUM = 2;
        private final String[] fieldsLabel = {"Games Played", "Games Won", "Win Percentage"};
        private String[] fieldsValue;

        public UserStatisticsTableModel() {

            Statistic statistic = AccountManager.getInstance().getStatistic();

            setNumRows(ROWS_NUM);
            setColumnCount(COLS_NUM);

            fieldsValue = new String[ROWS_NUM];
            fieldsValue[0] = String.valueOf(statistic.playNum);
            fieldsValue[1] = String.valueOf(statistic.winNum);
            fieldsValue[2] = String.valueOf(statistic.winPercent);

            for (int i = 0; i < ROWS_NUM; i++) {
                setValueAt(fieldsLabel[i], i, 0);
                setValueAt(fieldsValue[i], i, 1);
            }

        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private class AllStatisticsTableModel extends DefaultTableModel {

        private Object[][] data;
        private Object[] columnNames = {"Username", "Wins", "Win Percentage"};

        public AllStatisticsTableModel() {

            AccountManager accountManager = AccountManager.getInstance();
            Map<AccountInfo, Statistic> allStatistics = accountManager.getAllAccountsStatistics();
            data = new Object[allStatistics.size()][columnNames.length];
            int index = 0;

            for (AccountInfo accountInfo : allStatistics.keySet()) {
                Statistic statistic = allStatistics.get(accountInfo);
                Object[] rowData = {accountInfo.username, statistic.winNum, statistic.winPercent};
                data[index] = rowData;
                index++;
            }

            setDataVector(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}

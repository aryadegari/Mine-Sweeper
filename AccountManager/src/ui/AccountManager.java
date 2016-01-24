package ui;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hp on 09/12/2015.
 */
public class AccountManager {

    private static final HashMap<AccountInfo, Statistic> accountMap = new HashMap<>();
    private static AccountManager ourInstance = new AccountManager();

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        return ourInstance;
    }

    public boolean addAcount(AccountInfo accountInfo) {
        return false;
    }

    public void editAccount(AccountInfo accountInfo) {

    }

    public void deleteAccount(AccountInfo accountInfo) {

    }

    public AccountInfo getAccountInfo(AccountInfo accountInfo) {
        return null;
    }

    public List<AccountInfo> getAllAccountInfo() {
        return null;
    }

    public Statistic getStatistics(AccountInfo accountInfo) {
        Statistic statistic = accountMap.get(accountInfo);

        if (statistic == null) {
            statistic = Statistic.getDefault();
            accountMap.put(accountInfo, statistic);
        }
        return accountMap.get(accountInfo);
    }
}

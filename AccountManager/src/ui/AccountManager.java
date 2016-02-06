package ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.GameState;
import data.GameStateMemento;
import utils.Consts;
import utils.MapUtil;

import java.lang.reflect.Type;
import java.util.*;
import java.util.prefs.Preferences;

/**
 * Created by hp on 09/12/2015.
 */
public class AccountManager {

    private static final String ACCOUNTS_PREFIX = "account_data_";
    private static final String ACCOUNTS_ACCOUNT_PREFIX = ACCOUNTS_PREFIX + "account_";
    private static final String ACCOUNTS_STATISTICS_PREFIX = ACCOUNTS_PREFIX + "statistics_";
    private static final String ACCOUNTS_GAME_STATE_PREFIX = ACCOUNTS_PREFIX + "game_state_";
    private static final String ACCOUNTS_USER_NAMES_KEY = ACCOUNTS_PREFIX + "user_names_key";
    private static final AccountInfo GUEST_ACCOUNT = new AccountInfo(Consts.ACCOUNTS_GUEST_USER_NAME, Consts.ACCOUNTS_GUEST_PASSWORD);
    private static AccountInfo accountInfo;
    private static Statistic statistic;
    private static GameState gameState;
    private static HashSet<String> usernames;
    private static AccountManager ourInstance = new AccountManager();

    private AccountManager() {
        Preferences prefs = getPreferences();
        if (prefs.get(ACCOUNTS_ACCOUNT_PREFIX + Consts.ACCOUNTS_GUEST_USER_NAME, null) == null) {
            addAccount(GUEST_ACCOUNT);
        }
        Type type = new TypeToken<HashSet<String>>() {
        }.getType();
        usernames = new Gson().fromJson(prefs.get(ACCOUNTS_USER_NAMES_KEY, new Gson().toJson(new HashSet<String>())), type);
    }

    public static AccountManager getInstance() {
        return ourInstance;
    }

    private static Preferences getPreferences() {
        return Preferences.userRoot().node(AccountManager.class.getName());
    }

    public AccountInfo getAccountInfo(String username) {
        Preferences prefs = getPreferences();
        return new Gson().fromJson(prefs.get(ACCOUNTS_ACCOUNT_PREFIX + username, null), AccountInfo.class);
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        if (accountInfo == null || getAccountInfo(accountInfo.username) == null) {
            System.out.println("AccountManager.setAccountInfo account is null or not exists! " + accountInfo);
            return;
        }
        AccountManager.accountInfo = accountInfo;
        statistic = getStatistic(accountInfo);
        gameState = loadGameState();
    }

    public boolean addAccount(AccountInfo accountInfo) {
        if (getAccountInfo(accountInfo.username) != null) return false;
        saveAccount(accountInfo);
        addUsername(accountInfo.username);
        return true;
    }

    private void addUsername(String username) {
        Preferences prefs = getPreferences();
        Type type = new TypeToken<HashSet<String>>() {
        }.getType();
        HashSet<String> usernamesSet = new Gson().fromJson(prefs.get(ACCOUNTS_USER_NAMES_KEY, new Gson().toJson(new HashSet<String>())), type);
        usernamesSet.add(username);
        prefs.put(ACCOUNTS_USER_NAMES_KEY, new Gson().toJson(usernamesSet));
        usernames = usernamesSet;
    }

    private void deleteUsername(String username) {
        Preferences prefs = getPreferences();
        usernames.remove(username);
        prefs.put(ACCOUNTS_USER_NAMES_KEY, new Gson().toJson(usernames));
    }

    private void saveAccount(AccountInfo accountInfo) {
        Preferences prefs = getPreferences();
        prefs.put(ACCOUNTS_ACCOUNT_PREFIX + accountInfo.username, new Gson().toJson(accountInfo));
    }

    public void editAccount(AccountInfo accountInfo) {
        AccountInfo accountInfoFromDB = getAccountInfo(accountInfo.username);
        if (accountInfoFromDB == null) return;
        saveAccount(accountInfo);
    }

    public boolean deleteAccount(AccountInfo accountInfo) {
        Preferences prefs = getPreferences();
        if (getAccountInfo(accountInfo.username) == null) return false;
        prefs.remove(ACCOUNTS_ACCOUNT_PREFIX + accountInfo.username);
        deleteUsername(accountInfo.username);
        return true;
    }

    public List<AccountInfo> getAllAccountsInfo() {
        List<AccountInfo> accounts = new ArrayList<>();
        for (String username : usernames) {
            AccountInfo accountInformation = getAccountInfo(username);
            accounts.add(accountInformation);
        }
        return accounts;
    }

    public Map<AccountInfo, Statistic> getAllAccountsStatistics() {
        HashMap<AccountInfo, Statistic> statisticsMap = new HashMap<>();
        List<AccountInfo> allAccounts = getAllAccountsInfo();
        for (AccountInfo accountInfo : allAccounts) {
            statisticsMap.put(accountInfo, getStatistic(accountInfo));
        }
        return MapUtil.sortByValue(statisticsMap);
    }

    private Statistic getStatistic(AccountInfo accountInfo) {
        if (accountInfo == null || getAccountInfo(accountInfo.username) == null) {
            System.out.println("AccountManager.getStatistics account is null or not exists! " + accountInfo);
            return Statistic.getDefault();
        }
        Preferences prefs = getPreferences();
        return new Gson().fromJson(
                prefs.get(ACCOUNTS_STATISTICS_PREFIX + accountInfo.username,
                        new Gson().toJson(Statistic.getDefault())), Statistic.class);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void updateStats(boolean win) {
        statistic.update(win);
        saveStatistic();
    }

    private void saveStatistic() {
        Preferences prefs = getPreferences();
        prefs.put(ACCOUNTS_STATISTICS_PREFIX + accountInfo.username, new Gson().toJson(statistic));
    }

    private GameState loadGameState() {
        Preferences prefs = getPreferences();
        GameState gameStateDefault = GameState.getDefault();
        String state = prefs.get(ACCOUNTS_GAME_STATE_PREFIX + accountInfo.username, gameStateDefault.toMemento().getState());
        return GameState.fromMemento(new GameStateMemento(state));
    }

    public GameState getGameState() {
        return gameState;
    }

    public void saveGameState(GameState gameState) {
        GameStateMemento gameStateMemento = gameState.toMemento();
        Preferences prefs = getPreferences();
        prefs.put(ACCOUNTS_GAME_STATE_PREFIX + accountInfo.username, gameStateMemento.getState());
        AccountManager.gameState = gameState;
    }

    public void resetStats() {
        statistic.reset();
        saveStatistic();
    }
}

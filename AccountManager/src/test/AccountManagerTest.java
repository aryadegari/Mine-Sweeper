package test;

import data.GameState;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ui.AccountInfo;
import ui.AccountManager;
import ui.Statistic;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hp on 05/02/2016.
 */
public class AccountManagerTest {

    AccountInfo initialAccount;
    AccountManager accountManager;

    @Before
    public void setUp() throws Exception {
        accountManager = AccountManager.getInstance();
        String username = "username";
        String password = "password";
        AccountInfo account = testAccountAdd(username, password);
        accountManager.setAccountInfo(account);
    }

    @After
    public void tearDown() throws Exception {
        testDeleteAccount();
    }

    private AccountInfo testAccountAdd(String username, String password) {

        initialAccount = new AccountInfo(username, password);
        AccountInfo expected = initialAccount;
        accountManager.addAccount(expected);
        AccountInfo actual = accountManager.getAccountInfo(initialAccount.username);
        assertEquals(expected.username, actual.username);
        assertEquals(expected.password, actual.password);
        return actual;
    }

    @Test
    public void testEditAccount() {

        String newPassword = "new" + initialAccount.password;
        AccountInfo expected = accountManager.getAccountInfo(initialAccount.username);
        expected.password = newPassword;
        accountManager.editAccount(expected);

        AccountInfo actual = accountManager.getAccountInfo(initialAccount.username);
        assertEquals(actual.username, expected.username);
        assertEquals(actual.password, expected.password);
    }

    public void testDeleteAccount() {

        boolean success = accountManager.deleteAccount(initialAccount);
        assertTrue(success);

        AccountInfo accountInfo = accountManager.getAccountInfo(initialAccount.username);
        assertNull(accountInfo);
    }

    @Test
    public void testGetAllAccountsInfo() {

        String prefix = initialAccount.username;
        int start = 1;
        int end = 100;
        List<AccountInfo> accounts = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            accounts.add(testAccountAdd(prefix + i, prefix + i));
        }
        boolean contains = accountManager.getAllAccountsInfo().containsAll(accounts);
        assertTrue(contains);
    }

    @Test
    public void testStatistic() {

        Statistic resetStatistic = accountManager.resetStats();
        assertEquals(resetStatistic.playNum, 0);
        assertEquals(resetStatistic.winNum, 0);
        assertEquals(resetStatistic.winPercent, 0);

        accountManager.updateStats(true);
        Statistic statistic = accountManager.getStatistic();
        assertEquals(statistic.playNum, 1);
        assertEquals(statistic.winNum, 1);
        assertEquals(statistic.winPercent, 100);
    }

    @Test
    public void testGameState() {

        accountManager.setAccountInfo(initialAccount);
        GameState gameState = accountManager.getGameState();
        assertNotNull(gameState);
        assertTrue(gameState.getTimeSecs() == 0);
        assertTrue(gameState.getNumberOfMoves() == 0);

        gameState.getBoard().openCell(0, 0);
        accountManager.saveGameState(gameState);
        accountManager.setAccountInfo(initialAccount);
        GameState actual = accountManager.getGameState();
        assertEquals(gameState, actual);
    }
}

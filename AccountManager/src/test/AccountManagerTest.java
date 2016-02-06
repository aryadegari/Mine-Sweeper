package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ui.AccountInfo;
import ui.AccountManager;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

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
        testAccountAdd(username, password);
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

    private void testDeleteAccount() {

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
}

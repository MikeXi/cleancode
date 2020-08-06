package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.epam.engx.cleancode.functions.task1.thirdpartyjar.CheckStatus.OK;

public class RegisterAccountAction {


    private PasswordChecker passwordChecker;
    private AccountManager accountManager;

    public void registerAccount(Account account) {
        validateAccountNameAndPassword(account);

        createAccount(account);
    }

    private void createAccount(Account account) {
        account.setCreatedDate(new Date());
        setAccountAddress(account);
        accountManager.createNewAccount(account);
    }

    private void setAccountAddress(Account account) {
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(account.getHomeAddress());
        addresses.add(account.getWorkAddress());
        addresses.add(account.getAdditionalAddress());
        account.setAddresses(addresses);
    }

    private void validateAccountNameAndPassword(Account account) {
        validateAccountName(account);
        String password = getPassword(account);
        validateAccountPassword(password);
    }

    private void validateAccountPassword(String password) {
        if (isInvalidPassword(password)) {
            throw new WrongPasswordException();
        }
    }

    private boolean isInvalidPassword(String password) {
        return passwordChecker.validate(password) != OK;
    }

    private String getPassword(Account account) {
        String password = account.getPassword();
        if (password.length() <= 8) {
            throw new WrongPasswordException();
        }
        return password;
    }

    private void validateAccountName(Account account) {
        if (account.getName().length() <= 5){
            throw new WrongAccountNameException();
        }
    }


    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public void setPasswordChecker(PasswordChecker passwordChecker) {

        this.passwordChecker = passwordChecker;
    }

}

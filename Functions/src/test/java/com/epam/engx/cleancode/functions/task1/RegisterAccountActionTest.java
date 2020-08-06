package com.epam.engx.cleancode.functions.task1;

import com.epam.engx.cleancode.functions.task1.thirdpartyjar.*;
import org.junit.Before;
import org.junit.Test;

public class RegisterAccountActionTest {

    private AccountManagerMock accountManagerMock;

    private final RegisterAccountAction registerAccountAction = new RegisterAccountAction();
    private final Account validAccountStub = new ValidAccountStub();
    private final ValidAccountMock validAccountMock = new ValidAccountMock();

    @Before
    public void setUp() throws Exception {
        accountManagerMock = new AccountManagerMock();
        registerAccountAction.setAccountManager(accountManagerMock);
        registerAccountAction.setPasswordChecker(new OkPasswordChecker());
    }

    @Test
    public void shouldRegisterAccount() {
        registerAccountAction.registerAccount(validAccountStub);
        accountManagerMock.assertAccountRegistered(validAccountStub);
    }

    @Test
    public void shouldPopulateAccountWhenCreate() {
        registerAccountAction.registerAccount(validAccountMock);
        validAccountMock.assertCreationDateExist();
        validAccountMock.assertHomeAddressInAddresses();
        validAccountMock.assertWorkAddressInAddresses();
        validAccountMock.assertAdditionalAddressInAddresses();
    }

    @Test (expected = WrongAccountNameException.class)
    public void shouldThrowExceptionWhenNameIsTooShort() {
        registerAccountAction.registerAccount(new ShortNameAccountStub());
    }

    @Test (expected = WrongPasswordException.class)
    public void shouldThrowExceptionWhenPasswordIsTooShort() {
        registerAccountAction.registerAccount(new ShortPasswordAccountStub());
    }


    @Test (expected = WrongPasswordException.class)
    public void shouldThrowExceptionWhenPasswordIsNotOk() {
        registerAccountAction.setPasswordChecker(new NotOkPasswordChecker());
        registerAccountAction.registerAccount(validAccountStub);
    }

}

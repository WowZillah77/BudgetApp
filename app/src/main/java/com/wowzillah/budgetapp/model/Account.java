package com.wowzillah.budgetapp.model;

/**
 * Created by david on 19/10/17.
 */

public class Account {
    private int id;
    private String accoundName;
    private AccountType accountType;
    private double accountBalance;

    public Account(int id, String accoundName, AccountType accountType, double accountBalance) {
        this.id = id;
        this.accoundName = accoundName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public Account(String accoundName, AccountType accountType, double accountBalance) {
        this.accoundName = accoundName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccoundName() {
        return accoundName;
    }

    public void setAccoundName(String accoundName) {
        this.accoundName = accoundName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}

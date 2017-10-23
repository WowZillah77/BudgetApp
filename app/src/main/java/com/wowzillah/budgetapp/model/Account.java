package com.wowzillah.budgetapp.model;

/**
 * Created by david on 19/10/17.
 */

public class Account {
    private int id;
    private String accountName;
    private AccountType accountType;
    private double accountBalance;

    public Account(int id, String accountName, AccountType accountType, double accountBalance) {
        this.id = id;
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public Account(String accountName, AccountType accountType, double accountBalance) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.accountBalance = accountBalance;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

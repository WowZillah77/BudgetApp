package com.wowzillah.budgetapp;

import com.wowzillah.budgetapp.model.Account;
import com.wowzillah.budgetapp.model.AccountType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 19/10/17.
 */

public class GenererAccount {

    public static List<Account> genererAccounts() {

        List<Account> accountsList = new ArrayList<>();
        AccountType checkingAcount = new AccountType( "Checking Account", "bank");
        Account mainaccount = new Account("banque postale", checkingAcount, 256.20);
        for (int i = 0; i < 3; i++) {
            accountsList.add(mainaccount);

        }


        return accountsList;
    }
}

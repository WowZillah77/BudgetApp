package com.wowzillah.budgetapp.init;

import android.content.Context;

import com.wowzillah.budgetapp.DAO.AccountDAO;
import com.wowzillah.budgetapp.DAO.AccountTypeDAO;
import com.wowzillah.budgetapp.model.Account;
import com.wowzillah.budgetapp.model.AccountType;
import com.wowzillah.budgetapp.sqlite.DBHelper;

import java.util.List;

/**
 * Created by david on 24/10/17.
 */

public class Initialize {



   public static void initialize(Context context){
       DBHelper dbHelper = new DBHelper(context);
       String[] types ={"Checking Account", "Saving Account", "Cash Account", "Blocked Saving Account", "Goal"};
        AccountTypeDAO accountTypeDAO = new AccountTypeDAO(context);
        List<AccountType> accountTypes= accountTypeDAO.getAllAccountType();
       AccountDAO accountDAO = new AccountDAO(context);

        if(accountTypes.size()==0){
            for (int i = 0; i < types.length; i++) {
                AccountType accountType = new AccountType();
                accountType.setName(types[i]);
                accountType.setTypeIcon("bank.png");
                accountTypeDAO.createAccountType(accountType);


            }
            AccountType accountType =accountTypeDAO.getAccountTypeById(0);
            //Test Account
            Account account = new Account("test Account",accountType,0.00);
            accountDAO.createAccount(account);

        }


    }
}

package com.wowzillah.budgetapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.wowzillah.budgetapp.adapters.AccountAdapter;
import com.wowzillah.budgetapp.model.Account;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=(ListView) findViewById(R.id.accountListView);
        List<Account> accounts = GenererAccount.genererAccounts();

        AccountAdapter adapter = new AccountAdapter(MainActivity.this, accounts);
        mListView.setAdapter(adapter);

    }

}

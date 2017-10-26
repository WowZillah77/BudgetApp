package com.wowzillah.budgetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.wowzillah.budgetapp.DAO.AccountDAO;
import com.wowzillah.budgetapp.adapters.AccountAdapter;
import com.wowzillah.budgetapp.init.Initialize;
import com.wowzillah.budgetapp.model.Account;
import com.wowzillah.budgetapp.sqlite.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView=(ListView) findViewById(R.id.accountListView);

        Initialize.initialize(this);
        AccountDAO accountDAO=new AccountDAO(this);
        List<Account> accounts = accountDAO.getAllAccount();

        AccountAdapter adapter = new AccountAdapter(MainActivity.this, accounts);
        mListView.setAdapter(adapter);

    }

    public void clickTest(View view){
        Intent intent = new Intent(this,CreateAccount.class);
        startActivity(intent);
    }

}

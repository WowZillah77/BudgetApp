package com.wowzillah.budgetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.wowzillah.budgetapp.DAO.AccountDAO;
import com.wowzillah.budgetapp.DAO.AccountTypeDAO;
import com.wowzillah.budgetapp.init.Initialize;
import com.wowzillah.budgetapp.model.Account;
import com.wowzillah.budgetapp.model.AccountType;

import java.util.List;

public class CreateAccount extends AppCompatActivity {
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Initialize.initialize(this);
        AccountTypeDAO accountTypeDAO = new AccountTypeDAO(this);
        List<AccountType> accountType = accountTypeDAO.getAllAccountType();

        setContentView(R.layout.activity_create_account);
        spinner = (Spinner) findViewById(R.id.accountTypeSpinner);
        ArrayAdapter<AccountType> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accountType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setSelection(1);
        spinner.setAdapter(adapter);





    }

    public void onValidate(View view){
        AccountDAO accountDAO= new AccountDAO(this);
        AccountType accountType= (AccountType) spinner.getSelectedItem();
        EditText accountName= (EditText) findViewById(R.id.accountnameEditText);
        EditText accountAmount = (EditText) findViewById(R.id.accountAmountEditText);
        Account account = new Account();
        String name=accountName.getText().toString();
        float amount=Float.valueOf(accountAmount.getText().toString());
        account.setAccountName(name);
        account.setAccountBalance(amount);
        account.setAccountType(accountType);
       Account accountcreated = accountDAO.createAccount(account);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);




    }
}

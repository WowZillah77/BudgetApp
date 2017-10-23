package com.wowzillah.budgetapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wowzillah.budgetapp.model.Account;
import com.wowzillah.budgetapp.model.AccountType;
import com.wowzillah.budgetapp.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 23/10/17.
 */

public class AccountDAO {

    public static final String TAG="CompanyDAO";

    // Database Fields

    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns ={DBHelper.COLUMN_ACCOUNT_ID, DBHelper.COLUMN_TYPENAME, DBHelper.COLUMN_TYPEICON};

    public AccountDAO(Context context){
        this.mContext =  context;
        mDbHelper = new DBHelper(context);
        //Open the Database
        try{
            open();
        } catch (SQLException e){
            Log.e(TAG, "SQLException on opening database"+ e.getMessage());
            e.printStackTrace();
        }
    }

    public void open() throws SQLException{
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){

        mDbHelper.close();
    }

    public Account createAccount(Account account){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, account.getAccountName());
        values.put(DBHelper.COLUMN_TYPE, account.getAccountType().getId());
        values.put(DBHelper.COLUMN_BALANCE, account.getAccountBalance());
        long insertId = mDatabase.insert(DBHelper.TABLE_ACCOUNT,null,values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_ACCOUNT, mAllColumns,
                DBHelper.COLUMN_ACCOUNT_ID +" = " +insertId,null,null,null,null);
        cursor.moveToFirst();
        Account newAccount = cursorToAccount(cursor);
        cursor.close();
        return newAccount;

    }

    public void deleteAccount(Account account){
        long id= account.getId();
        mDatabase.delete(DBHelper.TABLE_ACCOUNT, DBHelper.COLUMN_ACCOUNT_ID + " = "+ id,null);
    }

    public List<Account> getAllAccount(){
        List<Account> listAccount = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_ACCOUNT, mAllColumns, null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Account account = cursorToAccount(cursor);
            listAccount.add(account);
            cursor.moveToNext();

        }
        //make sure to close the cursor
        cursor.close();
        return listAccount;
    }

    public Account getAccountById(int Id) {
        Account account = new Account();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_ACCOUNT, mAllColumns,
                DBHelper.COLUMN_ACCOUNT_ID + " = " + Id, null, null, null, null, null);
        account.setId(Id);
        account.setAccountName(cursor.getString(1));

        //get the Account Type
        int typeId = cursor.getInt(2);
        AccountTypeDAO dao = new AccountTypeDAO(mContext);
        AccountType accountType = dao.getAccountTypeById(typeId);
        if(accountType != null)
            account.setAccountType(accountType);
        //End finding type

        account.setAccountBalance(cursor.getFloat(3));


        return account;
    }

    private Account cursorToAccount(Cursor cursor){
        Account account = new Account();
        account.setId(cursor.getInt(0));
        account.setAccountName(cursor.getString(1));
        account.setAccountBalance(cursor.getFloat(3));

        //get the type by ID
        int typeId = cursor.getInt(2);
        AccountTypeDAO dao = new AccountTypeDAO(mContext);
        AccountType accountType = dao.getAccountTypeById(typeId);
        if(accountType != null)
            account.setAccountType(accountType);

        return account;



    }



}

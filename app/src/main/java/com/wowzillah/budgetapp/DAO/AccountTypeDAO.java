package com.wowzillah.budgetapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wowzillah.budgetapp.model.AccountType;
import com.wowzillah.budgetapp.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 23/10/17.
 */

public class AccountTypeDAO {

    public static final String TAG = "AccountTypeDAO";

    //Database Fields

    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllComumns = {DBHelper.COLUMN_ACCOUNTTYPE_ID, DBHelper.COLUMN_TYPENAME, DBHelper.COLUMN_TYPEICON};

    public AccountTypeDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        //open the Database
        try {
            open();
        } catch (SQLException e) {
            Log.e(TAG, "SQLException on opening database" + e.getMessage());
            e.printStackTrace();
        }

    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public AccountType createAccountType(AccountType accountType) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_TYPENAME, accountType.getName());
        values.put(DBHelper.COLUMN_TYPEICON, accountType.getTypeIcon());

        long insertId = mDatabase.insert(DBHelper.TABLE_ACCOUNTTYPE, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_ACCOUNTTYPE, mAllComumns,
                DBHelper.COLUMN_ACCOUNTTYPE_ID + " = " + insertId, null, null, null, null, null);
        cursor.moveToFirst();
        AccountType newAccountType = cursorToAccountType(cursor);
        cursor.close();
        return newAccountType;
    }

    public void deleteAccountType(AccountType accountType) {
        long id = accountType.getId();
        mDatabase.delete(DBHelper.TABLE_ACCOUNTTYPE, DBHelper.COLUMN_ACCOUNTTYPE_ID + " = " + id, null);
    }


    public List<AccountType> getAllAccountType() {
        List<AccountType> listAccountType = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_ACCOUNTTYPE, mAllComumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            AccountType accountType = cursorToAccountType(cursor);
            listAccountType.add(accountType);
            cursor.moveToNext();

        }
        //make sure to close the cursor
        cursor.close();
        return listAccountType;
    }

    public AccountType getAccountTypeById(int typeId) {


        Cursor cursor = mDatabase.query(DBHelper.TABLE_ACCOUNTTYPE, mAllComumns,
                DBHelper.COLUMN_ACCOUNTTYPE_ID + " = ?", new String[] { String.valueOf(typeId)},null, null, null);
        if(cursor !=null)
        {
            cursor.moveToFirst();
        }
        AccountType accountType = cursorToAccountType(cursor);



        return accountType;
    }


    private AccountType cursorToAccountType(Cursor cursor) {
        AccountType accountType = new AccountType();
        accountType.setId(cursor.getInt(0));
        accountType.setName(cursor.getString(1));
        accountType.setTypeIcon(cursor.getString(2));
        return accountType;
    }
}

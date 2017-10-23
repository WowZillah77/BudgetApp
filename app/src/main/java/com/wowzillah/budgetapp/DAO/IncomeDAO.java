package com.wowzillah.budgetapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wowzillah.budgetapp.model.Account;
import com.wowzillah.budgetapp.model.Income;
import com.wowzillah.budgetapp.model.IncomeCategory;
import com.wowzillah.budgetapp.sqlite.DBHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by david on 23/10/17.
 */

public class IncomeDAO {

    public static final String TAG = "IncomeDAO";

    // Database Fields

    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DBHelper.COLUMN_INCOME_ID, DBHelper.COLUMN_INCOME_NAME, DBHelper
            .COLUMN_INCOME_AMOUNT, DBHelper.COLUMN_INCOME_CATEGORY, DBHelper.COLUMN_INCOME_DATE, DBHelper.COLUMN_INCOME_ACCOUNT};

    public IncomeDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
        //Open the Database
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

    public Income createIncome(Income income) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_INCOME_NAME, income.getIncomeName());
        values.put(DBHelper.COLUMN_INCOME_AMOUNT, income.getIncomeAmount());
        values.put(DBHelper.COLUMN_INCOME_CATEGORY, income.getIncomeCategory().getId());
        //convert Date to String
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String date = df.format(income.getDateIncome());
        values.put(DBHelper.COLUMN_INCOME_DATE, date);
        //
        values.put(DBHelper.COLUMN_INCOME_ACCOUNT, income.getAccount().getId());
        long insertId = mDatabase.insert(DBHelper.TABLE_INCOME, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_INCOME, mAllColumns, DBHelper.COLUMN_INCOME_ID + " = "
                + insertId, null, null, null, null);
        cursor.moveToFirst();
        Income newIncome = cursorToIncome(cursor);
        cursor.close();
        return newIncome;
    }

    public void deleteIncome(Income income) {
        long id = income.getId();
        mDatabase.delete(DBHelper.TABLE_INCOME, DBHelper.COLUMN_INCOME_ID + " = " + id, null);
    }

    public List<Income> getAllIncome() {
        List<Income> listIncome = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_INCOME, mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Income income = cursorToIncome(cursor);
            listIncome.add(income);
            cursor.moveToNext();

        }
        //make sure to close the cursor
        cursor.close();
        return listIncome;
    }

    public Income getIncomeById(int id) {
        Income income = new Income();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_INCOME, mAllColumns, DBHelper.COLUMN_INCOME_ID + " = " + id, null, null, null, null);
        income.setId(id);
        income.setIncomeName(cursor.getString(1));
        income.setIncomeAmount(cursor.getFloat(2));

        // get the income Category
        int categoryId = cursor.getInt(3);
        IncomeCategoryDAO incomeCategoryDAO = new IncomeCategoryDAO(mContext);
        IncomeCategory incomeCategory = incomeCategoryDAO.getIncomeCategoryById(categoryId);
        if (incomeCategory != null)
            income.setIncomeCategory(incomeCategory);

        //get the income Date
        String categoryStringDate = cursor.getString(4);
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        try {
            Date myDate = df.parse(categoryStringDate);
            income.setDateIncome(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //get the Account object
        int accountId = cursor.getInt(5);
        AccountDAO accountDao = new AccountDAO(mContext);
        Account account = accountDao.getAccountById(accountId);
        if (account != null)
            income.setAccount(account);


        return income;
    }


    private Income cursorToIncome(Cursor cursor) {
        Income income = new Income();
        income.setId(cursor.getInt(0));
        income.setIncomeName(cursor.getString(1));
        income.setIncomeAmount(cursor.getFloat(2));

        // get the income category by id
        int categoryId = cursor.getInt(3);
        IncomeCategoryDAO dao = new IncomeCategoryDAO(mContext);
        IncomeCategory incomeCategory = dao.getIncomeCategoryById(categoryId);
        if (incomeCategory != null)
            income.setIncomeCategory(incomeCategory);

        //get the date of the income
        String date = cursor.getString(4);
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        try {
            Date myDate = df.parse(date);
            income.setDateIncome(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //get the account object
        int accountId = cursor.getInt(5);
        AccountDAO accountDao = new AccountDAO(mContext);
        Account account = accountDao.getAccountById(accountId);
        if (account != null)
            income.setAccount(account);
        return income;


    }

}


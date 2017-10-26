package com.wowzillah.budgetapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wowzillah.budgetapp.model.Account;
import com.wowzillah.budgetapp.model.Expense;
import com.wowzillah.budgetapp.model.ExpenseCategory;
import com.wowzillah.budgetapp.sqlite.DBHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by david on 23/10/17.
 */

public class ExpenseDAO {

    public static final String TAG = "ExpenseDAO";

    // Database Fields

    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllColumns = {DBHelper.COLUMN_EXPENSE_ID, DBHelper.COLUMN_EXPENSE_NAME, DBHelper
            .COLUMN_EXPENSE_AMOUNT, DBHelper.COLUMN_EXPENSE_CATEGORY, DBHelper.COLUMN_EXPENSE_DATE, DBHelper.COLUMN_EXPENSE_ACCOUNT};

    public ExpenseDAO(Context context) {
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

    public Expense createExpense(Expense expense) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_EXPENSE_NAME, expense.getName());
        values.put(DBHelper.COLUMN_EXPENSE_AMOUNT, expense.getExpenseAmount());
        values.put(DBHelper.COLUMN_EXPENSE_CATEGORY, expense.getExpenseCategory().getId());
        //convert Date to String
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String date = df.format(expense.getExpenseDate());
        values.put(DBHelper.COLUMN_EXPENSE_DATE, date);
        //
        values.put(DBHelper.COLUMN_EXPENSE_ACCOUNT, expense.getAccount().getId());
        long insertId = mDatabase.insert(DBHelper.TABLE_EXPENSE, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_EXPENSE, mAllColumns, DBHelper.COLUMN_EXPENSE_ID + " = "
                + insertId, null, null, null, null);
        cursor.moveToFirst();
        Expense newExpense = cursorToExpense(cursor);
        cursor.close();
        return newExpense;
    }

    public void deleteExpense(Expense expense) {
        long id = expense.getId();
        mDatabase.delete(DBHelper.TABLE_EXPENSE, DBHelper.COLUMN_EXPENSE_ID + " = " + id, null);
    }

    public List<Expense> getAllExpense() {
        List<Expense> listExpense = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_EXPENSE, mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Expense expense = cursorToExpense(cursor);
            listExpense.add(expense);
            cursor.moveToNext();

        }
        //make sure to close the cursor
        cursor.close();
        return listExpense;
    }

    public Expense getExpenseById(int id) {

        Cursor cursor = mDatabase.query(DBHelper.TABLE_EXPENSE, mAllColumns, DBHelper.COLUMN_EXPENSE_ID + " = ?",new String[] { String.valueOf(id)},null,null,null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        Expense expense = cursorToExpense(cursor);
        return expense;
    }


    private Expense cursorToExpense(Cursor cursor) {
        Expense expense = new Expense();
        expense.setId(cursor.getInt(0));
        expense.setName(cursor.getString(1));
        expense.setExpenseAmount(cursor.getFloat(2));

        // get the income category by id
        int categoryId = cursor.getInt(3);
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO(mContext);
        ExpenseCategory expenseCategory = dao.getExpenseCategoryById(categoryId);
        if (expenseCategory != null)
            expense.setExpenseCategory(expenseCategory);

        //get the date of the income
        String date = cursor.getString(4);
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        try {
            Date myDate = df.parse(date);
            expense.setExpenseDate(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //get the account object
        int accountId = cursor.getInt(5);
        AccountDAO accountDao = new AccountDAO(mContext);
        Account account = accountDao.getAccountById(accountId);
        if (account != null)
            expense.setAccount(account);
        return expense;
    }

    }

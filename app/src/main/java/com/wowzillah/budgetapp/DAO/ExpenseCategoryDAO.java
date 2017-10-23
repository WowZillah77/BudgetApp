package com.wowzillah.budgetapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wowzillah.budgetapp.model.ExpenseCategory;
import com.wowzillah.budgetapp.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 23/10/17.
 */

public class ExpenseCategoryDAO {

    public static final String TAG = "ExpenseCategoryDAO";

    //DatabaseFields

    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllComumns = {DBHelper.COLUMN_EXPENSECATEGORY_ID, DBHelper.COLUMN_EXPENSECATEGORY_NAME, DBHelper.COLUMN_EXPENSECATEGORY_ICON, DBHelper.COLUMN_EXPENSECATEGORY_TOTAL_AMOUNT};

    public ExpenseCategoryDAO(Context context) {
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

    public ExpenseCategory createExpenseCategory(ExpenseCategory expenseCategory){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_EXPENSE_NAME,expenseCategory.getExpenseCategoryName());
        values.put(DBHelper.COLUMN_EXPENSECATEGORY_ICON,expenseCategory.getExpenseCategoryIcon());
        values.put(DBHelper.COLUMN_EXPENSECATEGORY_TOTAL_AMOUNT,expenseCategory.getCategoryTotalAmount());

        long insertId = mDatabase.insert(DBHelper.TABLE_EXPENSECATEGORY, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_EXPENSECATEGORY, mAllComumns,
                DBHelper.COLUMN_EXPENSECATEGORY_ID + " = " + insertId, null, null, null, null, null);
        cursor.moveToFirst();
        ExpenseCategory newExpenseCategory = cursorToExpenseCategory(cursor);
        cursor.close();
        return newExpenseCategory;

    }

    public void deleteExpenseCategory(ExpenseCategory expenseCategory) {
        long id = expenseCategory.getId();
        mDatabase.delete(DBHelper.TABLE_EXPENSECATEGORY, DBHelper.COLUMN_EXPENSECATEGORY_ID + " = " + id, null);
    }


    public List<ExpenseCategory> getAllExpenseCategory() {
        List<ExpenseCategory> listExpenseCategory = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_EXPENSECATEGORY, mAllComumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ExpenseCategory expenseCategory = cursorToExpenseCategory(cursor);
            listExpenseCategory.add(expenseCategory);
            cursor.moveToNext();

        }
        //make sure to close the cursor
        cursor.close();
        return listExpenseCategory;
    }

    public ExpenseCategory getExpenseCategoryById(int categoryId) {
        ExpenseCategory expenseCategory = new ExpenseCategory();

        Cursor cursor = mDatabase.query(DBHelper.TABLE_EXPENSECATEGORY, mAllComumns,
                DBHelper.COLUMN_EXPENSECATEGORY_ID + " = " + categoryId, null, null, null, null, null);
        expenseCategory.setId(categoryId);
        expenseCategory.setExpenseCategoryName(cursor.getString(1));
        expenseCategory.setExpenseCategoryIcon(cursor.getString(2));
        expenseCategory.setCategoryTotalAmount(cursor.getFloat(3));

        return expenseCategory;
    }



    private ExpenseCategory cursorToExpenseCategory(Cursor cursor) {
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setId(cursor.getInt(0));
        expenseCategory.setExpenseCategoryName(cursor.getString(1));
        expenseCategory.setExpenseCategoryIcon(cursor.getString(2));
        expenseCategory.setCategoryTotalAmount(cursor.getFloat(3));
        return expenseCategory;
    }
}

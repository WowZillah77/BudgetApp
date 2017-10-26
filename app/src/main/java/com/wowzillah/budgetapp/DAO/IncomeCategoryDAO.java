package com.wowzillah.budgetapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wowzillah.budgetapp.model.IncomeCategory;
import com.wowzillah.budgetapp.sqlite.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 23/10/17.
 */

public class IncomeCategoryDAO {

    public static final String TAG = "IncomeCategoryDAO";

    //DatabaseFields

    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private Context mContext;
    private String[] mAllComumns = {DBHelper.COLUMN_INCOMECATEGORY_ID, DBHelper.COLUMN_INCOMECATEGORY_NAME, DBHelper.COLUMN_INCOMECATEGORY_ICON, DBHelper.COLUMN_INCOMECATEGORY_TOTAL_AMOUNT};

    public IncomeCategoryDAO(Context context) {
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

    public IncomeCategory createIncomeCategory(IncomeCategory incomeCategory){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_INCOMECATEGORY_NAME,incomeCategory.getName());
        values.put(DBHelper.COLUMN_INCOMECATEGORY_ICON,incomeCategory.getIcon());
        values.put(DBHelper.COLUMN_INCOMECATEGORY_TOTAL_AMOUNT,incomeCategory.getTotalamountIncome());

        long insertId = mDatabase.insert(DBHelper.TABLE_INCOMECATEGORY, null, values);
        Cursor cursor = mDatabase.query(DBHelper.TABLE_INCOMECATEGORY, mAllComumns,
                DBHelper.COLUMN_INCOMECATEGORY_ID + " = " + insertId, null, null, null, null, null);
        cursor.moveToFirst();
       IncomeCategory newIncomeCategory = cursorToIncomeCategory(cursor);
        cursor.close();
        return newIncomeCategory;

    }

    public void deleteIncomeCategory(IncomeCategory incomeCategory) {
        long id =incomeCategory.getId();
        mDatabase.delete(DBHelper.TABLE_INCOMECATEGORY, DBHelper.COLUMN_INCOMECATEGORY_ID + " = " + id, null);
    }


    public List<IncomeCategory> getAllIncomeCategory() {
        List<IncomeCategory> listIncomeCategory = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_INCOMECATEGORY, mAllComumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            IncomeCategory incomeCategory = cursorToIncomeCategory(cursor);
            listIncomeCategory.add(incomeCategory);
            cursor.moveToNext();

        }
        //make sure to close the cursor
        cursor.close();
        return listIncomeCategory;
    }

    public IncomeCategory getIncomeCategoryById(int categoryId) {


        Cursor cursor = mDatabase.query(DBHelper.TABLE_INCOMECATEGORY, mAllComumns,
                DBHelper.COLUMN_INCOMECATEGORY_ID + " = ?" ,new String[] { String.valueOf(categoryId)}, null, null, null);
        if(cursor !=null){
            cursor.moveToFirst();
        }
        IncomeCategory incomeCategory = cursorToIncomeCategory(cursor);
        return incomeCategory;
    }



    private IncomeCategory cursorToIncomeCategory(Cursor cursor) {
        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setId(cursor.getInt(0));
        incomeCategory.setName(cursor.getString(1));
        incomeCategory.setIcon(cursor.getString(2));
        incomeCategory.setTotalamountIncome(cursor.getFloat(3));
        return incomeCategory;
    }
}

package com.wowzillah.budgetapp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by david on 19/10/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    //columns of the Account table
    public static final String TABLE_ACCOUNT = "account";
    public static final String COLUMN_ACCOUNT_ID= "_id";
    public static final String COLUMN_NAME = "account_name";
    public static final String COLUMN_TYPE = "account_type";
    public static final String COLUMN_BALANCE= "account_balance";

    //column  of the Accounttype table
    public static final String TABLE_ACCOUNTTYPE = "account";
    public static final String COLUMN_ACCOUNTTYPE_ID= "_id";
    public static final String COLUMN_TYPENAME = "account_type_name";
    public static final String COLUMN_TYPEICON="account_type_icon";

    //column of the Expense table
    public static final String TABLE_EXPENSE ="expense";
    public static final String COLUMN_EXPENSE_ID = "_id";
    public static final String COLUMN_EXPENSE_NAME = "expense_name" ;
    public static final String COLUMN_EXPENSE_AMOUNT = "expense_amount" ;
    public static final String COLUMN_EXPENSE_CATEGORY = "expense_category" ;
    public static final String COLUMN_EXPENSE_DATE = "expense_date" ;


    //column of the expensecategory table
    public static final String TABLE_EXPENSECATEGORY ="expense_category";
    public static final String COLUMN_EXPENSECATEGORY_ID = "_id";
    public static final String COLUMN_EXPENSECATEGORY_NAME = "expense_category_name" ;
    public static final String COLUMN_EXPENSECATEGORY_TOTAL_AMOUNT = "income_category_total_amount";

    //column of the income table
    public static final String TABLE_INCOME ="income";
    public static final String COLUMN_INCOME_ID = "_id";
    public static final String COLUMN_INCOME_NAME = "income_name" ;
    public static final String COLUMN_INCOME_AMOUNT = "income_amount" ;
    public static final String COLUMN_INCOME_CATEGORY = "income_category" ;
    public static final String COLUMN_INCOME_DATE = "income_date" ;


    //column of the income category table
    public static final String TABLE_INCOMECATEGORY ="income_category";
    public static final String COLUMN_INCOMECATEGORY_ID = "_id";
    public static final String COLUMN_INCOMECATEGORY_NAME = "income_category_name" ;
    public static final String COLUMN_INCOMECATEGORY_TOTAL_AMOUNT = "income_category_Total_Amount";


    private static final String DATABASE_NAME="budget.db";
    private static final int DATABASE_VERSION =1;

    //SQL statement of the AccountType table creation

    private static final String SQL_CREATE_TABLE_ACCOUNTTYPE = "CREATE TABLE" + TABLE_ACCOUNTTYPE + "("
            +COLUMN_ACCOUNTTYPE_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_TYPENAME + "TEXT NOT NULL,"
            +COLUMN_TYPEICON + "TEXT NOT NULL"
            +");";

    //SQL statement of the ExpenseCategory table creation

    private static final String SQL_CREATE_TABLE_EXPENSECATEGORY = "CREATE TABLE"+ TABLE_EXPENSECATEGORY + "("
            +COLUMN_EXPENSECATEGORY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_EXPENSECATEGORY_NAME+ "TEXT NOT NULL,"
            +COLUMN_EXPENSECATEGORY_TOTAL_AMOUNT + "DOUBLE DEFAULT 0.00"
            +");";

    //SQL statement of the IncomeCategory table creation

    private static final String SQL_CREATE_TABLE_INCOMECATEGORY = "CREATE TABLE"+ TABLE_INCOMECATEGORY + "("
            +COLUMN_INCOMECATEGORY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_INCOMECATEGORY_NAME+ "TEXT NOT NULL,"
            +COLUMN_INCOMECATEGORY_TOTAL_AMOUNT + "DOUBLE DEFAULT 0.00"
            +");";

    //SQL statement of the Account table creation

    private static final String SQL_CREATE_TABLE_ACCOUNT = "CREATE TABLE"+ TABLE_ACCOUNT+"("
            +COLUMN_ACCOUNT_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_NAME+ "TEXT NOT NULL,"
            +COLUMN_TYPE+"INTEGER NOT NULL,"
            +"FOREIGN KEY ("+COLUMN_TYPE+") REFERENCES"+ TABLE_ACCOUNTTYPE+"("+COLUMN_ACCOUNTTYPE_ID+"),"
            +COLUMN_BALANCE+"DOUBLE DEFAULT 0.00"
            +");";





    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

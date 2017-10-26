package com.wowzillah.budgetapp.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by david on 19/10/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    //columns of the Account table
    public static final String TABLE_ACCOUNT = "_account";
    public static final String COLUMN_ACCOUNT_ID= "_id";
    public static final String COLUMN_NAME = "_account_name";
    public static final String COLUMN_TYPE = "_account_type_id";
    public static final String COLUMN_BALANCE= "_account_balance";

    //column  of the Accounttype table
    public static final String TABLE_ACCOUNTTYPE = "_account_type";
    public static final String COLUMN_ACCOUNTTYPE_ID= "_id";
    public static final String COLUMN_TYPENAME = "_account_type_name";
    public static final String COLUMN_TYPEICON="_account_type_icon";

    //column of the Expense table
    public static final String TABLE_EXPENSE ="_expense";
    public static final String COLUMN_EXPENSE_ID = "_id";
    public static final String COLUMN_EXPENSE_NAME = "_expense_name" ;
    public static final String COLUMN_EXPENSE_AMOUNT = "_expense_amount" ;
    public static final String COLUMN_EXPENSE_CATEGORY = "_expense_category" ;
    public static final String COLUMN_EXPENSE_DATE = "_expense_date" ;
    public static final String COLUMN_EXPENSE_ACCOUNT = "_expense_account";


    //column of the expensecategory table
    public static final String TABLE_EXPENSECATEGORY ="_expense_category";
    public static final String COLUMN_EXPENSECATEGORY_ID = "_id";
    public static final String COLUMN_EXPENSECATEGORY_NAME = "_expense_category_name" ;
    public static final String COLUMN_EXPENSECATEGORY_ICON="_expense_category_icon";
    public static final String COLUMN_EXPENSECATEGORY_TOTAL_AMOUNT = "_expense_category_total_amount";

    //column of the income table
    public static final String TABLE_INCOME ="_income";
    public static final String COLUMN_INCOME_ID = "_id";
    public static final String COLUMN_INCOME_NAME = "_income_name" ;
    public static final String COLUMN_INCOME_AMOUNT = "_income_amount" ;
    public static final String COLUMN_INCOME_CATEGORY = "_income_category" ;
    public static final String COLUMN_INCOME_DATE = "_income_date" ;
    public static final String COLUMN_INCOME_ACCOUNT = "_income_account";


    //column of the income category table
    public static final String TABLE_INCOMECATEGORY ="_income_category";
    public static final String COLUMN_INCOMECATEGORY_ID = "_id";
    public static final String COLUMN_INCOMECATEGORY_NAME = "_income_category_name" ;
    public static final String COLUMN_INCOMECATEGORY_ICON="_income_category_icon";
    public static final String COLUMN_INCOMECATEGORY_TOTAL_AMOUNT = "_income_category_total_amount";


    private static final String DATABASE_NAME="budget.db";
    private static final int DATABASE_VERSION =2;

    //SQL statement of the AccountType table creation

    private static final String SQL_CREATE_TABLE_ACCOUNTTYPE = "CREATE TABLE " + TABLE_ACCOUNTTYPE + " ("
            +COLUMN_ACCOUNTTYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_TYPENAME + " TEXT NOT NULL,"
            +COLUMN_TYPEICON + " TEXT NOT NULL"
            +");";

    //SQL statement of the ExpenseCategory table creation

    private static final String SQL_CREATE_TABLE_EXPENSECATEGORY = "CREATE TABLE "+ TABLE_EXPENSECATEGORY + "("
            +COLUMN_EXPENSECATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_EXPENSECATEGORY_NAME+ " TEXT NOT NULL,"
            +COLUMN_EXPENSECATEGORY_ICON+ " TEXT NOT NULL,"
            +COLUMN_EXPENSECATEGORY_TOTAL_AMOUNT + " REAL DEFAULT 0.00"
            +");";

    //SQL statement of the IncomeCategory table creation

    private static final String SQL_CREATE_TABLE_INCOMECATEGORY = "CREATE TABLE "+ TABLE_INCOMECATEGORY + " ("
            +COLUMN_INCOMECATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_INCOMECATEGORY_NAME+ " TEXT NOT NULL,"
            +COLUMN_INCOMECATEGORY_ICON+" TEXT NOT NULL,"
            +COLUMN_INCOMECATEGORY_TOTAL_AMOUNT + " REAL DEFAULT 0.00"
            +");";

    //SQL statement of the Account table creation
    private static final String SQL_CREATE_TABLE_ACCOUNT = "CREATE TABLE "+ TABLE_ACCOUNT+"("
            +COLUMN_ACCOUNT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_NAME+" TEXT,"
            +COLUMN_TYPE+" INTEGER NOT NULL,"
            +COLUMN_BALANCE+" REAL , "
            + "FOREIGN KEY("+COLUMN_TYPE +") REFERENCES " + TABLE_ACCOUNTTYPE+"("+COLUMN_ACCOUNTTYPE_ID+"))";



    //SQL statement of the expense creation table

    private static final String SQL_CREATE_TABLE_EXPENSE ="CREATE TABLE "+ TABLE_EXPENSE +"("
            +COLUMN_EXPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_EXPENSE_NAME+ " TEXT NOT NULL,"
            +COLUMN_EXPENSE_AMOUNT+" REAL DEFAULT 0.00,"
            +COLUMN_EXPENSE_CATEGORY +" INTEGER NOT NULL,"
            +COLUMN_EXPENSE_DATE+" TEXT DEFAULT NOW,"
            +COLUMN_EXPENSE_ACCOUNT+" INTEGER NOT NULL,"
            +"FOREIGN KEY("+COLUMN_EXPENSE_CATEGORY+") REFERENCES " + TABLE_EXPENSECATEGORY+"("+COLUMN_EXPENSECATEGORY_ID+"),"
            +"FOREIGN KEY("+COLUMN_EXPENSE_ACCOUNT+") REFERENCES "+ TABLE_ACCOUNT+"("+COLUMN_ACCOUNT_ID+"))";


    //SQL statement of the income creation table

    private static final String SQL_CREATE_TABLE_INCOME = "CREATE TABLE "+ TABLE_INCOME +"("
            +COLUMN_INCOME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_INCOME_NAME+ " TEXT NOT NULL,"
            +COLUMN_INCOME_AMOUNT+" REAL DEFAULT 0.00,"
            +COLUMN_INCOME_CATEGORY +" INTEGER NOT NULL,"
            +COLUMN_INCOME_DATE+" TEXT DEFAULT NOW,"
            +COLUMN_INCOME_ACCOUNT+" INTEGER NOT NULL,"
            +"FOREIGN KEY("+COLUMN_INCOME_CATEGORY+") REFERENCES "+ TABLE_INCOMECATEGORY+"("+COLUMN_INCOMECATEGORY_ID+"),"
            +"FOREIGN KEY("+COLUMN_INCOME_ACCOUNT+") REFERENCES "+ TABLE_ACCOUNT+"("+COLUMN_ACCOUNT_ID+"))";






    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("PRAGMA foreign_keys=ON");

        database.execSQL(SQL_CREATE_TABLE_EXPENSECATEGORY);
        database.execSQL(SQL_CREATE_TABLE_ACCOUNTTYPE);
        database.execSQL(SQL_CREATE_TABLE_INCOMECATEGORY);
        database.execSQL(SQL_CREATE_TABLE_ACCOUNT);
        database.execSQL(SQL_CREATE_TABLE_INCOME);
        database.execSQL(SQL_CREATE_TABLE_EXPENSE);





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //not the best way. need to set up a way to back up and restore date after upgrading
        Log.w(TAG,"Upgrading the database from version "+ oldVersion +" to "+ newVersion);
        //clear all data
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EXPENSECATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_INCOMECATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_INCOME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACCOUNTTYPE);

        //recreate Tables
        onCreate(db);

    }



    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
}

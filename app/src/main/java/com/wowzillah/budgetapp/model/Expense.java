package com.wowzillah.budgetapp.model;

import java.util.Date;

/**
 * Created by david on 19/10/17.
 */

public class Expense {
    private int id;
    private String name;
    private ExpenseCategory expenseCategory;
    private double expenseAmount;
    private Date expenseDate;

    public Expense(int id, String name, ExpenseCategory expenseCategory, double expenseAmount, Date expenseDate) {
        this.id = id;
        this.name = name;
        this.expenseCategory = expenseCategory;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
    }

    public Expense(String name, ExpenseCategory expenseCategory, double expenseAmount, Date expenseDate) {
        this.name = name;
        this.expenseCategory = expenseCategory;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.id=-1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }
}

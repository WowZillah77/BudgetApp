package com.wowzillah.budgetapp.model;

/**
 * Created by david on 19/10/17.
 */

public class ExpenseCategory {
    private int id;
    private String expenseCategoryName;
    private String expenseCategoryIcon;
    private double categoryTotalAmount;

    public ExpenseCategory(int id, String expenseCategoryName, String expenseCategoryIcon, double categoryTotalAmount) {
        this.id = id;
        this.expenseCategoryName = expenseCategoryName;
        this.expenseCategoryIcon = expenseCategoryIcon;
        this.categoryTotalAmount = categoryTotalAmount;
    }

    public ExpenseCategory(String expenseCategoryName, String expenseCategoryIcon, double categoryTotalAmount) {
        this.expenseCategoryName = expenseCategoryName;
        this.expenseCategoryIcon = expenseCategoryIcon;
        this.categoryTotalAmount = categoryTotalAmount;
    }

    public ExpenseCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpenseCategoryName() {
        return expenseCategoryName;
    }

    public void setExpenseCategoryName(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
    }

    public String getExpenseCategoryIcon() {
        return expenseCategoryIcon;
    }

    public void setExpenseCategoryIcon(String expenseCategoryIcon) {
        this.expenseCategoryIcon = expenseCategoryIcon;
    }

    public double getCategoryTotalAmount() {
        return categoryTotalAmount;
    }

    public void setCategoryTotalAmount(double categoryTotalAmount) {
        this.categoryTotalAmount = categoryTotalAmount;
    }
}
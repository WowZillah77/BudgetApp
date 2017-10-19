package com.wowzillah.budgetapp.model;

/**
 * Created by david on 19/10/17.
 */

class ExpenseCategory {
    private int id;
    private String expenseCategoryName;
    private double categoryTotalAmount;

    public ExpenseCategory(int id, String expenseCategoryName, double categoryTotalAmount) {
        this.id = id;
        this.expenseCategoryName = expenseCategoryName;
        this.categoryTotalAmount = categoryTotalAmount;
    }

    public ExpenseCategory(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
        this.categoryTotalAmount = 0;
        this.id=-1;
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

    public double getCategoryTotalAmount() {
        return categoryTotalAmount;
    }

    public void setCategoryTotalAmount(double categoryTotalAmount) {
        this.categoryTotalAmount = categoryTotalAmount;
    }
}

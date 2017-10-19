package com.wowzillah.budgetapp.model;

import java.util.Date;

/**
 * Created by david on 19/10/17.
 */

public class Income {
    private int id;
    private String incomeName;
    private double incomeAmount;
    private IncomeCategory incomeCategory;
    private Date dateIncome;

    public Income(int id, String incomeName, double incomeAmount, IncomeCategory incomeCategory, Date dateIncome) {
        this.id = id;
        this.incomeName = incomeName;
        this.incomeAmount = incomeAmount;
        this.incomeCategory = incomeCategory;
        this.dateIncome = dateIncome;
    }

    public Income(String incomeName, double incomeAmount, IncomeCategory incomeCategory, Date dateIncome) {
        this.incomeName = incomeName;
        this.incomeAmount = incomeAmount;
        this.incomeCategory = incomeCategory;
        this.dateIncome = dateIncome;
        this.id=-1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public IncomeCategory getIncomeCategory() {
        return incomeCategory;
    }

    public void setIncomeCategory(IncomeCategory incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    public Date getDateIncome() {
        return dateIncome;
    }

    public void setDateIncome(Date dateIncome) {
        this.dateIncome = dateIncome;
    }
}

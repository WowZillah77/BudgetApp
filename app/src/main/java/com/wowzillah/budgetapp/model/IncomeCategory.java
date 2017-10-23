package com.wowzillah.budgetapp.model;

/**
 * Created by david on 19/10/17.
 */

public class IncomeCategory {
    private int id;
    private String name;
    private String icon;
    private double totalamountIncome;

    public IncomeCategory(int id, String name, String icon, double totalamountIncome) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.totalamountIncome = totalamountIncome;

    }

    public IncomeCategory() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTotalamountIncome() {
        return totalamountIncome;
    }

    public void setTotalamountIncome(double totalamountIncome) {
        this.totalamountIncome = totalamountIncome;
    }
}


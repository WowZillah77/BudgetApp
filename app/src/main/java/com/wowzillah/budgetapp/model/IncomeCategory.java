package com.wowzillah.budgetapp.model;

/**
 * Created by david on 19/10/17.
 */

class IncomeCategory {
    private int id;
    private String name;
    private double totalamountIncome;

    public IncomeCategory(int id, String name, double totalamountIncome) {
        this.id = id;
        this.name = name;
        this.totalamountIncome = totalamountIncome;
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

    public double getTotalamountIncome() {
        return totalamountIncome;
    }

    public void setTotalamountIncome(double totalamountIncome) {
        this.totalamountIncome = totalamountIncome;
    }

    public IncomeCategory(String name) {
        this.name = name;
        this.id=-1;
        this.totalamountIncome=0;

    }
}

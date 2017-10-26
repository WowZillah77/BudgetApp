package com.wowzillah.budgetapp.model;

/**
 * Created by david on 19/10/17.
 */

public class AccountType {

    private int id;
    private String name;
    private String typeIcon;

    public AccountType(int id, String name, String typeIcon) {
        this.id = id;
        this.name = name;
        this.typeIcon = typeIcon;
    }

    @Override
    public String toString() {
        return name;
    }

    public AccountType() {
    }

    public AccountType(String name, String typeIcon) {
        this.name = name;
        this.typeIcon = typeIcon;
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

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }
}

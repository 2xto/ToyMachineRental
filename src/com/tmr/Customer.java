package com.tmr;

public class Customer {

    private String name,sureName;
    private int id;

    public Customer(int id, String name, String sureName){
        this.id=id;
        this.name=name;
        this.sureName=sureName;
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

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }


    @Override
    public String toString() {
        return "Customer ID: " +id +", (name:) " +name +", (surename:) "+sureName;
    }
}

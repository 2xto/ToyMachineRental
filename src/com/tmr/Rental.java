package com.tmr;

public class Rental {
    private int id;
    private String type, brand, model;
    private double price,deposit;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public Rental(int id, String type, String brand, String model, double price, double deposit){
        this.id=id;
        this.type=type;
        this.brand=brand;
        this.model=model;
        this.price=price;
        this.deposit=deposit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Rental with ID: " +id +" (type:) " +type +", (brand:) "
                +brand +", (model:) " +model +", (price:) " +price +", (deposit:) "+deposit;
    }
}

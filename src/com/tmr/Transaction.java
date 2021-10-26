package com.tmr;

public class Transaction {
    private int id, idCustomer, idRentals;

    @Override
    public String toString() {
        return "Transaction ID: " + id + ", customer ID:" + idCustomer + ", rental ID:" + idRentals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction(int id, int idCustomer, int idRentals){
        this.idCustomer=idCustomer;
        this.idRentals=idRentals;
        this.id=id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdRentals() {
        return idRentals;
    }

    public void setIdRentals(int idRentals) {
        this.idRentals = idRentals;
    }
}

package com.tmr;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RentalDatabase {
    private final String fileName;

    protected RentalDatabase(String fileName) {
        this.fileName = fileName;
    }

    private Connection connect() {
        String url = "jdbc:sqlite:" + fileName;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    protected void createCustomer(String name, String sureName) {
        String sql = "INSERT INTO customer (id_customer, name, sureName) VALUES (NULL,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, sureName);
            preparedStatement.executeUpdate();
            preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int givenID = resultSet.getInt(1);
                System.out.println("Created new customer entry:");
                printCustomer(givenID);
            }
        } catch (SQLException e) {
            System.err.println("creating customer error: " + name + " " + sureName);
            e.printStackTrace();
        }
    }

    protected void updateCustomer(int id, String name, String sureName) {
        String sql = "UPDATE customer SET name=?, sureName=? WHERE id_customer=?";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, sureName);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated customer entry:");
            printCustomer(id);
        } catch (SQLException e) {
            System.err.println("updating customer error: " + id);
            e.printStackTrace();
        }

    }

    protected List<Customer> listCustomers() {
        List<Customer> customers = new LinkedList<>();
        String sql = "SELECT * FROM customer;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            int id;
            String name, sureName;
            while (resultSet.next()) {
                id = resultSet.getInt("id_customer");
                name = resultSet.getString("name");
                sureName = resultSet.getString("sureName");
                customers.add(new Customer(id, name, sureName));
            }

        } catch (SQLException e) {
            System.err.println("showing customers error");
            e.printStackTrace();
        }
        return customers;
    }

    protected void showCustomers() {
        System.out.println("Customers list:");
        listCustomers().forEach(System.out::println);
    }

    protected void deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id_customer=?;";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            System.out.println("Deleted customer:");
            printCustomer(id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleting customer error: " + id);
            e.printStackTrace();
        }
    }

    protected void createRental(String type, String brand, String model, double price, double deposit) {
        String sql = "INSERT INTO rental (id_rental,type,brand,model,price,deposit) VALUES (NULL,?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, brand);
            preparedStatement.setString(3, model);
            preparedStatement.setDouble(4, price);
            preparedStatement.setDouble(5, deposit);
            preparedStatement.executeUpdate();
            preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int givenID = resultSet.getInt(1);
                System.out.println("Rental with ID: " + givenID + " (type:) " + type + ", (brand:) "
                        + brand + ", (model:) " + model + ", (price:) " + price + ", (deposit:) " + deposit + " has been created.");
            }
        } catch (SQLException e) {
            System.err.println("creating rental error: " + brand + " " + model);
        }
    }

    protected void updateRental(int id, String type, String brand, String model, double price, double deposit) {
        String sql = "UPDATE rental SET type=?, brand=?, model=?, price=?, deposit=? WHERE id_rental=?";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, brand);
            preparedStatement.setString(3, model);
            preparedStatement.setDouble(4, price);
            preparedStatement.setDouble(5, deposit);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            System.out.println("Updated entry:");
            printRental(id);
        } catch (SQLException e) {
            System.err.println("updating error ID: " + id);
            e.printStackTrace();
        }
    }

    protected void deleteRental(int id) {
        String sql = "DELETE FROM rental WHERE id_rental=?";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            System.out.println("Deleted rental:");
            printRental(id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleting rental error: " + id);
            e.printStackTrace();
        }

    }

    public void showRental() {
        System.out.println("Rentals list:");
        listRental().forEach(System.out::println);
    }

    private List<Rental> listRental() {
        List<Rental> rentals = new LinkedList<>();
        String sql = "SELECT * FROM rental;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            int id;
            String type, brand, model;
            double price, deposit;
            while (resultSet.next()) {
                id = resultSet.getInt("id_rental");
                type = resultSet.getString("type");
                brand = resultSet.getString("brand");
                model = resultSet.getString("model");
                price = resultSet.getDouble("price");
                deposit = resultSet.getDouble("deposit");
                rentals.add(new Rental(id, type, brand, model, price, deposit));
            }
        } catch (SQLException e) {
            System.err.println("showing customers error");
            e.printStackTrace();
        }
        return rentals;
    }

    public void createTransaction(int customerId, int rentalId) {
        String sql = "INSERT INTO transactions (id_transaction,id_customer,id_rental) VALUES (NULL,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, rentalId);
            preparedStatement.executeUpdate();
            preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                int givenID = resultSet.getInt(1);
                System.out.println("Transaction created with ID: " + givenID + " details below:");
                printCustomer(customerId);
                printRental(rentalId);
            }
        } catch (SQLException e) {
            System.err.println("creating transaction error");
            e.printStackTrace();
        }
    }

    protected void printRental(int rentalId) {
        listRental().stream().filter(r -> r.getId() == rentalId).forEach(System.out::println);
    }

    protected void printCustomer(int customerId) {
        listCustomers().stream().filter(c -> c.getId() == customerId).forEach(System.out::println);
    }


    protected void updateTransaction(int id, int customerId, int rentalId) {
        String sql = "UPDATE transactions SET id_customer=?, id_rental=? WHERE id_rental=?";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, rentalId);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updating transaction error ID " + id);
            e.printStackTrace();
        }
    }

    protected void closeTransaction(int rentalId) {
        String sql = "DELETE FROM transactions WHERE id_rental=?";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, rentalId);
            System.out.println("Closed transaction:");
            printRental(rentalId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("deleting rental error: " + rentalId);
            e.printStackTrace();
        }

    }


    protected List<Transaction> listTransactions() {
        List<Transaction> transactions = new LinkedList<>();
        String sql = "SELECT * FROM transactions;";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            int id, customerId, rentalId;
            while (resultSet.next()) {
                id = resultSet.getInt("id_transaction");
                customerId = resultSet.getInt("id_customer");
                rentalId = resultSet.getInt("id_rental");
                transactions.add(new Transaction(id, customerId, rentalId));
            }
        } catch (SQLException e) {
            System.err.println("listing transactions error");
            e.printStackTrace();
        }
        return transactions;


    }

    protected void showTransactions() {
        System.out.println("Transactions list:");
        for (Transaction t : listTransactions()) {
            System.out.println(t);
            printCustomer(t.getIdCustomer());
            printRental(t.getIdRentals());
            System.out.println("\n");
        }

    }

    protected List<Rental> listRentalsAvailable() {
        List<Rental> rentalsAvailable = new LinkedList<>();
        String sql = "SELECT * FROM rental WHERE id_rental NOT IN (SELECT id_rental FROM transactions);";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            int id;
            String type, brand, model;
            double price, deposit;
            while (resultSet.next()) {
                id = resultSet.getInt("id_rental");
                type = resultSet.getString("type");
                brand = resultSet.getString("brand");
                model = resultSet.getString("model");
                price = resultSet.getDouble("price");
                deposit = resultSet.getDouble("deposit");
                rentalsAvailable.add(new Rental(id, type, brand, model, price, deposit));
            }
        } catch (SQLException e) {
            System.err.println("listing available rentals error");
            e.printStackTrace();
        }
        return rentalsAvailable;
    }

    protected void showRentalAvailable() {
        System.out.println("Available rentals list:");
        listRentalsAvailable().forEach(System.out::println);
    }


    protected void showCustomerTransactions(int id) {
        System.out.println("Customer transactions List:");
        List<Transaction> trans = listTransactions().stream().filter(t -> t.getIdCustomer() == id).collect(Collectors.toList());
        trans.forEach(System.out::println);
        listCustomers().stream().filter(c -> c.getId() == id).forEach(System.out::println);
        for (Transaction t : trans) {
            printRental(t.getIdRentals());

        }
    }
}
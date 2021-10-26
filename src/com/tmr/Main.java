package com.tmr;

import java.sql.*;


public class Main {


    public static String getFileName() {
        return fileName;
    }

    static String fileName;

    public static void main(String[] args) {
        fileName="rental.db";
        createDatabase(fileName);
        createTable(fileName);

        Menu menu = Menu.getInstance();

        menu.mainMenu();




    }



    private static void createDatabase(String fileName) {
        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }
        } catch (SQLException e) {
            System.err.println("creating database error");
            e.printStackTrace();
        }
    }

    private static void createTable(String fileName) {
        String url = "jdbc:sqlite:" + fileName;



        String createCustomerTable = "CREATE TABLE IF NOT EXISTS customer (" +
                "id_customer INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "sureName TEXT);";
        String createRentalTable = "CREATE TABLE IF NOT EXISTS rental (" +
                "id_rental INTEGER PRIMARY KEY AUTOINCREMENT," +
                "type TEXT," +
                "brand TEXT," +
                "model TEXT," +
                "price REAL," +
                "deposit REAL);";
        String createTransactionTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id_transaction INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_customer INTEGER," +
                "id_rental INTEGER UNIQUE);";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Create new table

            stmt.execute(createCustomerTable);
            stmt.execute(createRentalTable);
            stmt.execute(createTransactionTable);

        } catch (SQLException e) {
            System.err.println("creating table error");
            e.printStackTrace();
        }
    }


}


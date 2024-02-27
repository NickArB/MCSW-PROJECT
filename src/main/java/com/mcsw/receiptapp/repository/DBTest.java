package com.mcsw.receiptapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTest {
    private static final String URL = "jdbc:h2:file:./db/database";
    private static final String USER = "reaper";
    private static final String PASSWORD = "d4p4ssw0rd";

    public static Connection getDBConnection() throws SQLException {
        try {
            // Load the driver for the DB
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void closeConnection(Connection conexion) throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}

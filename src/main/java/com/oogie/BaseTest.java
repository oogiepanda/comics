package com.oogie;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class BaseTest {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/comics";
    static final String USER = "root";
    //static final String USER = "admin";
    static final String PASS = "admin";
    static protected Connection conn = null;

    @BeforeAll
    public static void setUp() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanUp() {
        try {
            conn.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}

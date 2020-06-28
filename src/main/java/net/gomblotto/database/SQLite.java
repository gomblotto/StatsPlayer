package net.gomblotto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {
    private String path;
    protected Connection connection;

    public Connection getConnection() {
        return this.connection;
    }

    public SQLite(String path) throws ClassNotFoundException {
        this.path = path;
        Class.forName("org.sqlite.JDBC");
    }

    public void openConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connection = null;
    }

}

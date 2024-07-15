package com.IBook.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {
    // Método para obtener la conexión desde el DataSource
    public static Connection getConnection() throws SQLException, NamingException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/IBookDB");
        return ds.getConnection();
    }

    public static void main(String[] args) {
        String filePath = "./db/yourSQLFile.sql";
        executeSQLFile(filePath);
    }

    public static void executeSQLFile(String filePath) {
        try {
            // Read SQL file into a string
            String sql = new String(Files.readAllBytes(Paths.get(filePath)));

            // Establish database connection
            try (Connection conn = DatabaseConnection.getConnection();
                 Statement stmt = conn.createStatement()) {

                // Execute the SQL file
                stmt.execute(sql);
                System.out.println("SQL file executed successfully.");
            }
        } catch (SQLException | NamingException | IOException e) {
            e.printStackTrace();
        }
    }
}


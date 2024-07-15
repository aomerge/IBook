package com.IBook.service;

import com.IBook.model.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;

public class DatabaseService {

    // Función para verificar si la base de datos existe
    public boolean databaseExists(String databaseName) throws SQLException, NamingException {
        boolean exists = false;
        String query = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + databaseName + "'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                exists = true;
            }
        }
        return exists;
    }

     // Función para ejecutar el archivo SQL
    public void executeSQLFile() {
        DatabaseConnection.executeSQLFile("./db/createTable.sql");
    }

       // Método principal para pruebas
    public static void main(String[] args) {
        DatabaseService service = new DatabaseService();
        try {
            boolean exists = service.databaseExists("yourDatabaseName");
            System.out.println("Database exists: " + exists);

            if (!exists) {
                service.executeSQLFile();
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
    
}

package com.IBook.model;

import java.sql.*;

public class DatabaseConnection {
    // URL de conexión, usuario y contraseña deben ser modificados según tu configuración de MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/IBook?useSSL=false&serverTimezone=UTC";
    private static final String USER = "user";
    private static final String PASSWORD = "userpassword";

    // Carga del driver de MySQL JDBC
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver no encontrado");
            e.printStackTrace();
        }
    }

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTable (){
        
    }
    
}



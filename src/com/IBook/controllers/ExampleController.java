package com.IBook.controllers;

import com.IBook.model.DatabaseConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;

public class ExampleController {
    private String URLBookaApi = "https://gutendex.com/books";
    HttpURLConnection connection = null;


    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }

    public String getBooks(){
        return fetchBooks();
    }

    public String getCommprovationConnection(){
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                return "Success: Connected to the database.";
            } else {
                return "Error: Database connection was not established.";
            }
        } catch (SQLException e) {
            return("SQLException: " + e.getMessage());
        }
    }

    private String fetchBooks() {
        // URL del servidor de libros (reemplaza con la URL real)
        String booksUrl = "https://gutendex.com/books/";

        HttpURLConnection connection = null;
        try {
            URL url = new URL(booksUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder responseStrBuilder = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    responseStrBuilder.append(inputLine);
                }
                reader.close();

                return responseStrBuilder.toString();
            } else {
                return "Failed to get books: HTTP code " + responseCode;
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
}


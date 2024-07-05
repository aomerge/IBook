package com.IBook.controllers;

import com.IBook.model.DatabaseConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.IBook.controllers.interfaces.BookInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;

public class BookController implements BookInterface {
    private String URLBookApi = "https://gutendex.com";
    HttpURLConnection connection = null;

    public String getBooks(String page){
        return fetchBooks(page);
    }

   private String fetchBooks(String page) {
        // Construir la URL con el parámetro de página si está presente
        String booksUrl = this.URLBookApi + "/books/";
        if (page != null && !page.isEmpty()) {
            booksUrl += "?page=" + page;
        }

        HttpURLConnection connection = null;
        try {
            URL url = new URL(booksUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder responseStrBuilder = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    responseStrBuilder.append(inputLine);
                }
                reader.close();

                return responseStrBuilder.toString();
            } else {
                return "{\"error\": \"Failed to get books: HTTP code " + responseCode + "\"}";
            }
        } catch (Exception e) {
            return "{\"error\": \"Error: " + e.getMessage() + "\"}";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
}

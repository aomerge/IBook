package com.IBook.service;

import com.IBook.model.DatabaseConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.IBook.service.serviceDto.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;

public class BookService implements BookDTO {
    private String URLBookApi = "https://gutendex.com";
    HttpURLConnection connection = null;

    public String getBooks(){
        return fetchBooks(null, null, null);
    }

    public String getBooksPage(String page){
        return fetchBooks(page, null, null);
    }

    public String getBookById(String id){
        return fetchBooks(null, null, id);
    }

    public String getSearchedBooks(String search){
        return fetchBooks(null, search, null);
    }


    private String fetchBooks(String page, String search, String ids ) {
        // Construir la URL con el parámetro de página si está presente
        String booksUrl = this.URLBookApi + "/books/";

        if (page != null && !page.isEmpty()) {
            booksUrl += "?page=" + page;
        }

        if (search != null && !search.isEmpty()) {
            booksUrl += "?search=" + search;
        }

        if (ids != null && !ids.isEmpty()) {
            booksUrl += "?ids=" + ids;
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

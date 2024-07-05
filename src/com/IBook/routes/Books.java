package com.IBook;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.IBook.controllers.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;
import com.IBook.utils.*;
import com.IBook.model.*;
import com.google.gson.Gson;


public class Books extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         
        esponse.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        BookController Books = new BookController();
        String json = Books.getBooks();
        BooksModel JsonUtil = JsonUtils.jsonToBooks(json);      
        String unescapedJsonString = json.replace("\\\"", "\"");  
        response.getWriter().write(new Gson().toJson(unescapedJsonString));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Handle POST requests here
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Handle PUT requests here
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Handle DELETE requests here
    }

    private void fetchBooks(HttpServletResponse response) throws IOException {
        // Fetch books from the database
    }
}

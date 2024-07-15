package com.IBook;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
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
import com.IBook.service.*;
import com.IBook.service.serviceDto.*;


public class Books extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String pageParams = request.getParameter("page"); 
        String searchParams = request.getParameter("search");
        String idsParams = request.getParameter("ids");


        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        BookDTO jsonResponse = new BookService();
        PrintWriter out = response.getWriter();
        
        if (pageParams != null && !pageParams.equals("")) {
            out.print(jsonResponse.getBooksPage(pageParams));
        } else if (searchParams != null && !searchParams.equals("")) {
            out.print(jsonResponse.getSearchedBooks(searchParams));
        } else if (idsParams != null && !idsParams.equals("")) {
            out.print(jsonResponse.getBookById(idsParams));
        } else{
            out.print(jsonResponse.getBooks());
        }



        out.flush();   

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

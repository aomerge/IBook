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


public class Books extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExampleController controller = new ExampleController();
        response.getWriter().append("Goodbye from GoodbyeServlet!");
        //System.out.println(controller.getBooks());
        response.getWriter().write(controller.getBooks());
          response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello world! My first servlet</h1>");
        out.println("<h2>Products:</h2>");
        out.println("</html></body>");
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
}

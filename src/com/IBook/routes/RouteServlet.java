package com.IBook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import com.IBook.controllers.ExampleController;
import com.IBook.model.DatabaseConnection;


public class RouteServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

   
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExampleController controller = new ExampleController();
        response.getWriter().append("Hello from RouteServlet!");
        response.getWriter().append(controller.getBooks());
        response.getWriter().append(controller.getCommprovationConnection());
    }

}

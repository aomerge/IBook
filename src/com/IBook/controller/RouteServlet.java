package com.IBook;

import com.IBook.service.DatabaseService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.xml.crypto.Data;

public class RouteServlet extends HttpServlet {

    private DatabaseService databaseService;

    @Override
    public void init() throws ServletException {
        databaseService = new DatabaseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String databaseName = "IBook";
        boolean exists;        

        try {
            exists = databaseService.databaseExists(databaseName);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.getWriter().append("Error: ").append(e.getMessage());
            return;
        }

        response.getWriter().append("Database ").append(databaseName).append(" exists: ").append(String.valueOf(exists));

        try {
            databaseService.executeSQLFile();
            response.getWriter().append("SQL file executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().append("Error executing SQL file: ").append(e.getMessage());
        }
        
    }
}


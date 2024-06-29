package com.IBook.controllers;

import com.IBook.model.DatabaseConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ExampleController {

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }

    public String getBooks(){
        return "Mama";
    }
}


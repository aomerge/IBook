package com.IBook.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.IBook.model.LeadBookModel;
import com.IBook.service.LeadBooksService;
import com.IBook.service.serviceDto.LeadBookDTO;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.net.HttpURLConnection;
import java.sql.Connection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeadBooks extends HttpServlet {
    private  LeadBookDTO leadBookService = new LeadBooksService();

    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String leadBookId = request.getParameter("leadBookId");

        if (leadBookId == null) {
            
            List<LeadBookModel> leadBooks = leadBookService.getLeadBooks();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String leadBooksJson = gson.toJson(leadBooks);
        
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(leadBooksJson);
        }

        else {
            long id = Long.parseLong(leadBookId);
            LeadBookModel leadBook = leadBookService.getLeadBookById(id);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String leadBookJson = gson.toJson(leadBook);
        
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(leadBookJson);
        }
    }
}
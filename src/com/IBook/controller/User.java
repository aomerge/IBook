package com.IBook;

import com.IBook.service.UserService;
import com.IBook.service.serviceDto.UserDTO;
import com.IBook.model.UserModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
import java.util.List;

public class User extends HttpServlet {

    private UserDTO userService = new UserService();

    @Override
    public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String userId = request.getParameter("userId");

        if (userId == null) {
            
            List<UserModel> users = userService.getUsers(userId);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String usersJson = gson.toJson(users);
        
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(usersJson);
        }

        else {
            long id = Long.parseLong(userId);
            UserModel user = userService.getUserById(id);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String userJson = gson.toJson(user);
        
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(userJson);
        }


        
    }
}


package com.IBook.service;

import com.IBook.service.serviceDto.UserDTO;
import com.IBook.model.DatabaseConnection;
import com.IBook.model.UserModel;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserService implements UserDTO{

    private Connection connection;

    private List<UserModel> users = new ArrayList<>();

       
    public List<UserModel> getUsers(String page) {
        //List<UserModel> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Users")) {

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setCreateDate(rs.getTimestamp("createDate"));
                user.setUpdateDate(rs.getTimestamp("updateDate"));
                this.users.add(user);
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return this.users;
    }

    public UserModel getUserById(long id) {
        try {
            this.getUsers("1");
            Stream<UserModel> userStream = this.users.stream().filter(user -> user.getId() == id);
            UserModel user = userStream.findFirst().orElse(null);
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        
    }


}
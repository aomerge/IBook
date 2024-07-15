package com.IBook.service;

import com.IBook.model.LeadBookModel;
import com.IBook.service.serviceDto.LeadBookDTO;
import com.IBook.model.DatabaseConnection;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.PreparedStatement;


public class LeadBooksService implements  LeadBookDTO {

    private Connection connection;

    private List<LeadBookModel> leadBooks = new ArrayList<>();

    public List<LeadBookModel> getLeadBooks() {

        try (
                Connection connection = DatabaseConnection.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM LeadBooks")
            ){
                while (rs.next()) {
                    LeadBookModel leadBook = new LeadBookModel();
                    leadBook.setId(rs.getLong("id"));
                    leadBook.setBookId(rs.getLong("bookId"));
                    leadBook.setUserId(rs.getLong("userId"));
                    leadBook.setCheckoutDate(rs.getDate("checkoutDate"));
                    leadBook.setDueDate(rs.getDate("dueDate"));
                    leadBook.setReturnDate(rs.getDate("returnDate"));
                    leadBook.setConditionOnLoan(rs.getString("conditionOnLoan"));
                    leadBook.setConditionOnReturn(rs.getString("conditionOnReturn"));
                    leadBook.setRenewals(rs.getInt("renewals"));
                    leadBook.setLateFee(rs.getBigDecimal("lateFee"));
                    leadBooks.add(leadBook);
                    
                }                
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return this.leadBooks;
    }
    
    public LeadBookModel getLeadBookById(long id) {
        LeadBookModel leadBook = new LeadBookModel();
        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement stmt = connection.prepareStatement("SELECT * FROM LeadBooks WHERE id = ?");
            ){
                stmt.setLong(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {                    
                    leadBook.setId(rs.getLong("id"));
                    leadBook.setBookId(rs.getLong("bookId"));
                    leadBook.setUserId(rs.getLong("userId"));
                    leadBook.setCheckoutDate(rs.getDate("checkoutDate"));
                    leadBook.setDueDate(rs.getDate("dueDate"));
                    leadBook.setReturnDate(rs.getDate("returnDate"));
                    leadBook.setConditionOnLoan(rs.getString("conditionOnLoan"));
                    leadBook.setConditionOnReturn(rs.getString("conditionOnReturn"));
                    leadBook.setRenewals(rs.getInt("renewals"));
                    leadBook.setLateFee(rs.getBigDecimal("lateFee"));
                    leadBooks.add(leadBook);
                }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return leadBook;
    }
    
}
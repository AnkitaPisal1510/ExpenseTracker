package com.example.expensetracker.dao;

import com.example.expensetracker.model.Expense;
import java.sql.*;
import java.util.*;

public class ExpenseDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/expensetracker";
    private String jdbcUsername = "root";
    private String jdbcPassword = "yourpassword";
    
    public void addExpense(Expense ex) {
        String sql = "INSERT INTO expenses(user_id, description, amount, date) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, ex.getUserId());
            stmt.setString(2, ex.getDescription());
            stmt.setDouble(3, ex.getAmount());
            stmt.setString(4, ex.getDate());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<Expense> getExpensesByUser(int userId) {
        List<Expense> list = new ArrayList<>();
        String sql = "SELECT * FROM expenses WHERE user_id=?";
        try (Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Expense ex = new Expense();
                ex.setId(rs.getInt("id"));
                ex.setUserId(rs.getInt("user_id"));
                ex.setDescription(rs.getString("description"));
                ex.setAmount(rs.getDouble("amount"));
                ex.setDate(rs.getString("date"));
                list.add(ex);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}

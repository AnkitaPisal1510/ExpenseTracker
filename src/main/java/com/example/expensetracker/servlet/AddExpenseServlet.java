package com.example.expensetracker.servlet;

import com.example.expensetracker.dao.ExpenseDAO;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String description = request.getParameter("description");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String date = request.getParameter("date");

        Expense exp = new Expense();
        exp.setUserId(user.getId());
        exp.setDescription(description);
        exp.setAmount(amount);
        exp.setDate(date);

        new ExpenseDAO().addExpense(exp);
        response.sendRedirect("viewExpenses.jsp");
    }
}

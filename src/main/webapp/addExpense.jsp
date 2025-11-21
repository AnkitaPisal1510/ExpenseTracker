<%@ page import="com.example.expensetracker.model.User" %>
<%
User user = (User)session.getAttribute("user");
if (user == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>
<html>
<head><title>Add Expense</title></head>
<body>
<h2>Add Expense</h2>
<form action="addExpense" method="post">
  Description: <input type="text" name="description" required><br>
  Amount: <input type="number" name="amount" step="0.01" required><br>
  Date: <input type="date" name="date" required><br>
  <input type="submit" value="Add Expense">
</form>
</body>
</html>

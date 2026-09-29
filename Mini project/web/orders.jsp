<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>

<!DOCTYPE html>
<html>
<head>
    <title>My Orders</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h1 class="title">My Orders</h1>

<%
    List<Order> orders =
        (List<Order>) request.getAttribute("orders");
%>

<table>

<tr>
    <th>Order ID</th>
    <th>Total</th>
    <th>Date</th>
    <th>Status</th>
</tr>

<%
    if (orders != null) {

        for (Order o : orders) {
%>

<tr>
    <td>#<%= o.getId() %></td>
    <td>? <%= o.getTotal() %></td>
    <td><%= o.getOrderDate() %></td>
    <td><%= o.getStatus() %></td>
</tr>

<%
        }
    }
%>

</table>

<div class="center">
    <a class="btn" href="products">Shop More</a>
</div>

</body>
</html>
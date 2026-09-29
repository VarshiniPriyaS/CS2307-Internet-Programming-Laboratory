<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>

<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<nav>
    <h2>E-Shopping</h2>
    <div>
        <a href="index.jsp">Home</a>
        <a href="products">Products</a>
        <a href="cart">Cart</a>
        <a href="orders">Orders</a>
    </div>
</nav>

<h1 class="title">Our Products</h1>

<div class="products">

<%
    List<Product> products =
        (List<Product>) request.getAttribute("products");

    for (Product p : products) {
%>

<div class="card">

    <img src="<%= p.getImage() %>" alt="Product">

    <h3><%= p.getName() %></h3>

    <p><%= p.getDescription() %></p>

    <h3>? <%= p.getPrice() %></h3>

    <p>Stock: <%= p.getStock() %></p>

    <a class="btn"
       href="products?id=<%= p.getId() %>">
       View Details
    </a>

</div>

<%
    }
%>

</div>

</body>
</html>
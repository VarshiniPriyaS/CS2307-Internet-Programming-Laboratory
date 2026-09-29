<%@ page import="dao.ProductDAO" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Product> products =
        new ProductDAO().getAllProducts();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h1 class="title">Admin Dashboard</h1>

<div class="admin-box">

<h2>Add Product</h2>

<form action="addProduct" method="post">

    <input type="text" name="name"
           placeholder="Product Name" required>

    <input type="text" name="description"
           placeholder="Description">

    <input type="number" step="0.01"
           name="price"
           placeholder="Price" required>

    <input type="text" name="image"
           placeholder="Image URL">

    <input type="number"
           name="stock"
           placeholder="Stock" required>

    <button type="submit">Add Product</button>

</form>

</div>

<h2 class="title">Product List</h2>

<table>

<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Price</th>
    <th>Stock</th>
    <th>Delete</th>
</tr>

<%
    for (Product p : products) {
%>

<tr>

<td><%= p.getId() %></td>

<td><%= p.getName() %></td>

<td>? <%= p.getPrice() %></td>

<td><%= p.getStock() %></td>

<td>
<a class="delete"
   href="deleteProduct?id=<%= p.getId() %>"
   onclick="return confirm('Delete this product?')">
   Delete
</a>
</td>

</tr>

<%
    }
%>

</table>

</body>
</html>
<%@ page import="model.Product" %>

<%
    Product p =
        (Product) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="details">

    <img src="<%= p.getImage() %>" alt="Product">

    <div>
        <h1><%= p.getName() %></h1>

        <p><%= p.getDescription() %></p>

        <h2>? <%= p.getPrice() %></h2>

        <p>Available Stock: <%= p.getStock() %></p>

        <form action="cart" method="post">
            <input type="hidden"
                   name="action"
                   value="add">

            <input type="hidden"
                   name="productId"
                   value="<%= p.getId() %>">

            <button type="submit">
                Add to Cart
            </button>
        </form>

        <br>

        <a href="products">Back to Products</a>
    </div>

</div>

</body>
</html>
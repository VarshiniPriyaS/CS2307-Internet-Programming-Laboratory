<%@ page import="java.util.List" %>
<%@ page import="model.Cart" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h1 class="title">Shopping Cart</h1>

<%
    List<Cart> cart =
        (List<Cart>) request.getAttribute("cart");

    double total = 0;

    if (cart == null || cart.isEmpty()) {
%>

    <h2 class="center">Your cart is empty.</h2>
    <div class="center">
        <a class="btn" href="products">Continue Shopping</a>
    </div>

<%
    } else {
%>

<table>
<tr>
    <th>Product</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Subtotal</th>
    <th>Action</th>
</tr>

<%
    for (Cart c : cart) {

        total += c.getSubtotal();
%>

<tr>
    <td><%= c.getProductName() %></td>

    <td>? <%= c.getPrice() %></td>

    <td>
        <form action="cart" method="post">

            <input type="hidden"
                   name="action"
                   value="update">

            <input type="hidden"
                   name="cartId"
                   value="<%= c.getId() %>">

            <input type="number"
                   name="quantity"
                   value="<%= c.getQuantity() %>"
                   min="1">

            <button type="submit">Update</button>

        </form>
    </td>

    <td>? <%= c.getSubtotal() %></td>

    <td>
        <form action="cart" method="post">

            <input type="hidden"
                   name="action"
                   value="remove">

            <input type="hidden"
                   name="cartId"
                   value="<%= c.getId() %>">

            <button type="submit">Remove</button>

        </form>
    </td>
</tr>

<%
    }
%>

</table>

<h2 class="total">Total: ? <%= total %></h2>

<div class="center">
    <a class="btn" href="checkout.jsp">Checkout</a>
    <a class="btn" href="products">Continue Shopping</a>
</div>

<%
    }
%>

</body>
</html>
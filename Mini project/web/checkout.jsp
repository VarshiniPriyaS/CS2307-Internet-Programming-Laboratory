<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="form-box">

    <h2>Checkout</h2>

    <p>Your order is ready to place.</p>

    <form action="orders" method="post">

        <button type="submit">
            Place Order
        </button>

    </form>

    <br>

    <a href="cart">Back to Cart</a>

</div>

</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="form-box">
    <h2>Login</h2>

    <form action="login" method="post">

        <input type="email" name="email"
               placeholder="Enter Email" required>

        <input type="password" name="password"
               placeholder="Enter Password" required>

        <button type="submit">Login</button>

    </form>

    <%
        if (request.getParameter("error") != null) {
    %>
        <p class="error">
            <%= request.getParameter("error") %>
        </p>
    <%
        }
    %>

    <%
        if (request.getParameter("msg") != null) {
    %>
        <p class="success">Registration successful. Login now.</p>
    <%
        }
    %>

    <p>New user?
        <a href="register.jsp">Register</a>
    </p>
</div>

</body>
</html>
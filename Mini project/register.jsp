<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="form-box">
    <h2>Create Account</h2>

    <form action="register" method="post">

        <input type="text" name="name"
               placeholder="Enter Name" required>

        <input type="email" name="email"
               placeholder="Enter Email" required>

        <input type="password" name="password"
               placeholder="Enter Password" required>

        <button type="submit">Register</button>

    </form>

    <p>Already have an account?
        <a href="login.jsp">Login</a>
    </p>

    <%
        if (request.getParameter("error") != null) {
    %>
        <p class="error">
            <%= request.getParameter("error") %>
        </p>
    <%
        }
    %>
</div>

</body>
</html>
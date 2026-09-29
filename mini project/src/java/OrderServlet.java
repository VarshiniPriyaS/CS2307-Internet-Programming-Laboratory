package controller;

import dao.OrderDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        User user =
            (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        OrderDAO dao = new OrderDAO();

        request.setAttribute(
            "orders",
            dao.getOrders(user.getId())
        );

        request.getRequestDispatcher("orders.jsp")
               .forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        User user =
            (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        boolean result =
            new OrderDAO().placeOrder(user.getId());

        if (result) {
            response.sendRedirect("orders");
        } else {
            response.sendRedirect(
                "cart?error=Cart is empty"
            );
        }
    }
}
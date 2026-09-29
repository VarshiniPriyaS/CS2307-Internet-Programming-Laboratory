package controller;

import dao.CartDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CartDAO dao = new CartDAO();
        request.setAttribute("cart", dao.getCart(user.getId()));
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        CartDAO dao = new CartDAO();

        if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            dao.addToCart(user.getId(), productId);
        }

        if ("update".equals(action)) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            if (quantity > 0) {
                dao.updateQuantity(cartId, quantity, user.getId());
            }
        }

        if ("remove".equals(action)) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            dao.removeFromCart(cartId, user.getId());
        }

        response.sendRedirect("cart");
    }
}
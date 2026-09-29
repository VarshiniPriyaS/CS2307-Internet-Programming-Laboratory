package controller;

import dao.ProductDAO;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        ProductDAO dao = new ProductDAO();

        if (id != null) {
            Product product = dao.getProduct(Integer.parseInt(id));
            request.setAttribute("product", product);
            request.getRequestDispatcher("productDetails.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute("products", dao.getAllProducts());
            request.getRequestDispatcher("products.jsp")
                    .forward(request, response);
        }
    }
}
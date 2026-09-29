package dao;

import model.Cart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public boolean addToCart(int userId, int productId) {

        String checkSql =
                "SELECT id, quantity FROM cart "
                + "WHERE user_id=? AND product_id=?";

        String insertSql =
                "INSERT INTO cart(user_id,product_id,quantity) "
                + "VALUES(?,?,1)";

        String updateSql =
                "UPDATE cart SET quantity=quantity+1 "
                + "WHERE user_id=? AND product_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement check =
                    con.prepareStatement(checkSql);

            check.setInt(1, userId);
            check.setInt(2, productId);

            ResultSet rs = check.executeQuery();

            int result;

            if (rs.next()) {

                PreparedStatement update =
                        con.prepareStatement(updateSql);

                update.setInt(1, userId);
                update.setInt(2, productId);

                result = update.executeUpdate();

                update.close();

            } else {

                PreparedStatement insert =
                        con.prepareStatement(insertSql);

                insert.setInt(1, userId);
                insert.setInt(2, productId);

                result = insert.executeUpdate();

                insert.close();
            }

            rs.close();
            check.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public List<Cart> getCart(int userId) {

        List<Cart> list = new ArrayList<>();

        String sql =
                "SELECT c.id,c.user_id,c.product_id,c.quantity,"
                + "p.name,p.price,p.image "
                + "FROM cart c "
                + "JOIN products p "
                + "ON c.product_id=p.id "
                + "WHERE c.user_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Cart cart = new Cart();

                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));
                cart.setProductName(rs.getString("name"));
                cart.setPrice(rs.getDouble("price"));
                cart.setImage(rs.getString("image"));

                list.add(cart);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public boolean updateQuantity(int cartId,
                                  int quantity,
                                  int userId) {

        String sql =
                "UPDATE cart SET quantity=? "
                + "WHERE id=? AND user_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, quantity);
            ps.setInt(2, cartId);
            ps.setInt(3, userId);

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public boolean removeFromCart(int cartId,
                                  int userId) {

        String sql =
                "DELETE FROM cart "
                + "WHERE id=? AND user_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, cartId);
            ps.setInt(2, userId);

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public void clearCart(int userId) {

        String sql =
                "DELETE FROM cart WHERE user_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
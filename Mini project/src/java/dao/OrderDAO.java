package dao;

import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public boolean placeOrder(int userId) {

        Connection con = null;

        try {

            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            String cartSql =
                "SELECT c.product_id, c.quantity, p.price " +
                "FROM cart c JOIN products p " +
                "ON c.product_id=p.id " +
                "WHERE c.user_id=?";

            PreparedStatement ps =
                con.prepareStatement(cartSql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            double total = 0;

            List<Integer> productIds = new ArrayList<>();
            List<Integer> quantities = new ArrayList<>();
            List<Double> prices = new ArrayList<>();

            while (rs.next()) {

                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                total += price * quantity;

                productIds.add(productId);
                quantities.add(quantity);
                prices.add(price);
            }

            rs.close();
            ps.close();

            if (productIds.isEmpty()) {
                con.rollback();
                return false;
            }

            String orderSql =
                "INSERT INTO orders(user_id,total,status) " +
                "VALUES(?,?,?)";

            PreparedStatement orderPs =
                con.prepareStatement(
                    orderSql,
                    Statement.RETURN_GENERATED_KEYS
                );

            orderPs.setInt(1, userId);
            orderPs.setDouble(2, total);
            orderPs.setString(3, "Placed");

            orderPs.executeUpdate();

            ResultSet keys = orderPs.getGeneratedKeys();

            keys.next();

            int orderId = keys.getInt(1);

            keys.close();
            orderPs.close();

            String itemSql =
                "INSERT INTO order_items " +
                "(order_id,product_id,quantity,price) " +
                "VALUES(?,?,?,?)";

            PreparedStatement itemPs =
                con.prepareStatement(itemSql);

            for (int i = 0; i < productIds.size(); i++) {

                itemPs.setInt(1, orderId);
                itemPs.setInt(2, productIds.get(i));
                itemPs.setInt(3, quantities.get(i));
                itemPs.setDouble(4, prices.get(i));

                itemPs.addBatch();
            }

            itemPs.executeBatch();
            itemPs.close();

            String clearSql =
                "DELETE FROM cart WHERE user_id=?";

            PreparedStatement clearPs =
                con.prepareStatement(clearSql);

            clearPs.setInt(1, userId);

            clearPs.executeUpdate();

            clearPs.close();

            con.commit();

            return true;

        } catch (Exception e) {

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

            return false;

        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Order> getOrders(int userId) {

        List<Order> list = new ArrayList<>();

        String sql =
            "SELECT id,user_id,total,order_date,status " +
            "FROM orders " +
            "WHERE user_id=? " +
            "ORDER BY order_date DESC";

        try {

            Connection con =
                DBConnection.getConnection();

            PreparedStatement ps =
                con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                ps.executeQuery();

            while (rs.next()) {

                Order o = new Order();

                o.setId(rs.getInt("id"));
                o.setUserId(rs.getInt("user_id"));
                o.setTotal(rs.getDouble("total"));
                o.setOrderDate(rs.getTimestamp("order_date"));
                o.setStatus(rs.getString("status"));

                list.add(o);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
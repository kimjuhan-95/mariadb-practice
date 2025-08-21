package bookmall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.OrderVo;
import bookmall.vo.OrderBookVo;


public class OrderDao {
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
    }

    public void insert(OrderVo vo) {
        String sql = "INSERT INTO orders(user_no, number, payment, shipping, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, vo.getUserNo());
            pstmt.setString(2, vo.getNumber());
            pstmt.setInt(3, vo.getPayment());
            pstmt.setString(4, vo.getShipping());
            pstmt.setString(5, vo.getStatus());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) vo.setNo(rs.getLong(1));
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void insertBook(OrderBookVo vo) {
        String sql = "INSERT INTO order_book(order_no, book_no, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, vo.getOrderNo());
            pstmt.setLong(2, vo.getBookNo());
            pstmt.setInt(3, vo.getQuantity());
            pstmt.setInt(4, vo.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public OrderVo findByNoAndUserNo(Long orderNo, Long userNo) {
        String sql = "SELECT no, user_no, number, payment, shipping, status FROM orders WHERE no=? AND user_no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.setLong(2, userNo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                OrderVo vo = new OrderVo();
                vo.setNo(rs.getLong("no"));
                vo.setUserNo(rs.getLong("user_no"));
                vo.setNumber(rs.getString("number"));
                vo.setPayment(rs.getInt("payment"));
                vo.setShipping(rs.getString("shipping"));
                vo.setStatus(rs.getString("status"));
                return vo;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<OrderBookVo> findBooksByNoAndUserNo(Long orderNo, Long userNo) {
        List<OrderBookVo> list = new ArrayList<>();
        String sql = "SELECT ob.order_no, ob.book_no, ob.quantity, ob.price, b.title " +
                     "FROM order_book ob " +
                     "JOIN book b ON ob.book_no=b.no " +
                     "JOIN orders o ON ob.order_no=o.no " +
                     "WHERE ob.order_no=? AND o.user_no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.setLong(2, userNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderBookVo vo = new OrderBookVo();
                vo.setOrderNo(rs.getLong("order_no"));
                vo.setBookNo(rs.getLong("book_no"));
                vo.setQuantity(rs.getInt("quantity"));
                vo.setPrice(rs.getInt("price"));
                vo.setBookTitle(rs.getString("title"));
                list.add(vo);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void deleteBooksByNo(Long orderNo) {
        String sql = "DELETE FROM order_book WHERE order_no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteByNo(Long orderNo) {
        String sql = "DELETE FROM orders WHERE no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }

}

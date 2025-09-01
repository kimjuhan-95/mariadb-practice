package bookmall.dao;

import java.sql.*;
import java.util.*;
import bookmall.vo.OrderVo;
import bookmall.vo.OrderBookVo;

public class OrderDao {
    public void insert(OrderVo vo) {
        String sql = "insert into orders(user_no, number, payment, shipping, status) values(?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getUserName());
            pstmt.setLong(2, vo.getNumber());
            pstmt.setInt(3, vo.getPayment());
            pstmt.setString(4, vo.getShipping());
            pstmt.setString(5, vo.getStatus());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) vo.setNo(rs.getLong(1));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void insertBook(OrderBookVo vo) {
        String sql = "insert into orders_book(order_no, book_no, quantity, price) values(?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, vo.getOrderNo());
            pstmt.setLong(2, vo.getBookNo());
            pstmt.setInt(3, vo.getQuantity());
            pstmt.setInt(4, vo.getPrice());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) vo.setNo(rs.getLong(1));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public OrderVo findByNoAndUserNo(Long orderNo, Long userNo) {
        String sql = "select no, user_no, number, payment, shipping, status from orders where no=? and user_no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.setLong(2, userNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    OrderVo vo = new OrderVo();
                    vo.setNo(rs.getLong(1));
                    vo.setUserNo(rs.getLong(2));
                    vo.setNumber(rs.getString(3));
                    vo.setPayment(rs.getInt(4));
                    vo.setShipping(rs.getString(5));
                    vo.setStatus(rs.getString(6));
                    return vo;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public List<OrderBookVo> findBooksByNoAndUserNo(Long orderNo, Long userNo) {
        List<OrderBookVo> list = new ArrayList<>();
        String sql = "select ob.no, ob.order_no, ob.book_no, b.title, ob.quantity, ob.price " +
                     "from orders_book ob " +
                     "join orders o on ob.order_no=o.no " +
                     "join book b on ob.book_no=b.no " +
                     "where ob.order_no=? and o.user_no=? order by ob.no";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.setLong(2, userNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderBookVo vo = new OrderBookVo();
                    vo.setNo(rs.getLong(1));
                    vo.setOrderNo(rs.getLong(2));
                    vo.setBookNo(rs.getLong(3));
                    vo.setBookTitle(rs.getString(4));
                    vo.setQuantity(rs.getInt(5));
                    vo.setPrice(rs.getInt(6));
                    list.add(vo);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void deleteBooksByNo(Long orderNo) {
        String sql = "delete from orders_book where order_no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteByNo(Long orderNo) {
        String sql = "delete from orders where no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, orderNo);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
    }
}

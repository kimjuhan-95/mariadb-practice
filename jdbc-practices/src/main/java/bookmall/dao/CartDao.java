package bookmall.dao;

import java.sql.*;
import java.util.*;
import bookmall.vo.CartVo;

public class CartDao {
    public void insert(CartVo vo) {
        String sql = "insert into cart(user_no, book_no, quantity) values(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, vo.getUserNo());
            pstmt.setLong(2, vo.getBookNo());
            pstmt.setInt(3, vo.getQuantity());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) vo.setNo(rs.getLong(1));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<CartVo> findByUserNo(Long userNo) {
        List<CartVo> list = new ArrayList<>();
        String sql = "select c.no, c.book_no, b.title, c.quantity, b.price " +
                     "from cart c join book b on c.book_no=b.no " +
                     "where c.user_no=? order by c.no";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, userNo);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CartVo vo = new CartVo();
                    vo.setNo(rs.getLong(1));
                    vo.setBookNo(rs.getLong(2));
                    vo.setBookTitle(rs.getString(3));
                    vo.setQuantity(rs.getInt(4));
                    vo.setPrice(rs.getInt(5));
                    vo.setUserNo(userNo);
                    list.add(vo);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void deleteByUserNoAndBookNo(Long userNo, Long bookNo) {
        String sql = "delete from cart where user_no=? and book_no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, userNo);
            pstmt.setLong(2, bookNo);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
    }
}

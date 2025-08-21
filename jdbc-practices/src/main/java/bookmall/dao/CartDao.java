package bookmall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.CartVo;

public class CartDao {
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
    }

    public void insert(CartVo vo) {
        String sql = "INSERT INTO cart(user_no, book_no, quantity) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, vo.getUserNo());
            pstmt.setLong(2, vo.getBookNo());
            pstmt.setInt(3, vo.getQuantity());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) vo.setNo(rs.getLong(1));
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<CartVo> findByUserNo(Long userNo) {
        List<CartVo> list = new ArrayList<>();
        String sql = "SELECT c.no, c.user_no, c.book_no, c.quantity, b.title " +
                     "FROM cart c JOIN book b ON c.book_no=b.no WHERE c.user_no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, userNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CartVo vo = new CartVo();
                vo.setNo(rs.getLong("no"));
                vo.setUserNo(rs.getLong("user_no"));
                vo.setBookNo(rs.getLong("book_no"));
                vo.setQuantity(rs.getInt("quantity"));
                vo.setBookTitle(rs.getString("title"));
                list.add(vo);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void deleteByUserNoAndBookNo(Long userNo, Long bookNo) {
        String sql = "DELETE FROM cart WHERE user_no=? AND book_no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, userNo);
            pstmt.setLong(2, bookNo);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }

}

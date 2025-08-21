package bookmall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.BookVo;

public class BookDao {
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
    }

    public void insert(BookVo vo) {
        String sql = "INSERT INTO book(title, price, category_no) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setInt(2, vo.getPrice());
            pstmt.setLong(3, vo.getCategoryNo());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) vo.setNo(rs.getLong(1));
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<BookVo> findAll() {
        List<BookVo> list = new ArrayList<>();
        String sql = "SELECT no, title, price, category_no FROM book";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookVo vo = new BookVo();
                vo.setNo(rs.getLong("no"));
                vo.setTitle(rs.getString("title"));
                vo.setPrice(rs.getInt("price"));
                vo.setCategoryNo(rs.getLong("category_no"));
                list.add(vo);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void deleteByNo(Long no) {
        String sql = "DELETE FROM book WHERE no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, no);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }

}
}

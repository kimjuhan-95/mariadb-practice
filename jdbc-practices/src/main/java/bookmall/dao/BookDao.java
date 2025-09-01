package bookmall.dao;

import java.sql.*;
import java.util.*;
import bookmall.vo.BookVo;

public class BookDao {
    public void insert(BookVo vo) {
        String sql = "insert into book(title, price, category_no) values(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setInt(2, vo.getPrice());
            pstmt.setLong(3, vo.getCategoryNo());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) vo.setNo(rs.getLong(1));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<BookVo> findAll() {
        List<BookVo> list = new ArrayList<>();
        String sql = "select no, title, price, category_no from book order by no";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                BookVo vo = new BookVo();
                vo.setNo(rs.getLong(1));
                vo.setTitle(rs.getString(2));
                vo.setPrice(rs.getInt(3));
                vo.setCategoryNo(rs.getLong(4));
                list.add(vo);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void deleteByNo(Long no) {
        String sql = "delete from book where no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, no);
            pstmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
    }
}

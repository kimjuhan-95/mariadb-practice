package bookmall.dao;

import java.sql.*;
import java.util.*;
import bookmall.vo.CategoryVo;

public class CategoryDao {
    public void insert(CategoryVo vo) {
        String sql = "insert into category(name) values(?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getName());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) vo.setNo(rs.getLong(1));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<CategoryVo> findAll() {
        List<CategoryVo> list = new ArrayList<>();
        String sql = "select no, name from category order by no";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                CategoryVo vo = new CategoryVo();
                vo.setNo(rs.getLong(1));
                vo.setName(rs.getString(2));
                list.add(vo);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void deleteByNo(Long no) {
        String sql = "delete from category where no=?";
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

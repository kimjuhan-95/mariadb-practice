package bookmall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.CategoryVo;

public class CategoryDao {
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
		
	}
	
	public void insert(CategoryVo vo) {
        String sql = "INSERT INTO category(name) VALUES (?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, vo.getName());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) vo.setNo(rs.getLong(1));
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<CategoryVo> findAll() {
        List<CategoryVo> list = new ArrayList<>();
        String sql = "SELECT no, name FROM category";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CategoryVo vo = new CategoryVo();
                vo.setNo(rs.getLong("no"));
                vo.setName(rs.getString("name"));
                list.add(vo);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void deleteByNo(Long no) {
        String sql = "DELETE FROM category WHERE no=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, no);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
	}

}

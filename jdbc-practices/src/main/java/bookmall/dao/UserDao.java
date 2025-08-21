package bookmall.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.UserVo;

public class UserDao {
	private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
        
	}
        public void insert(UserVo vo) {
            String sql = "INSERT INTO user(name, email, password, phone) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, vo.getName());
                pstmt.setString(2, vo.getEmail());
                pstmt.setString(3, vo.getPassword());
                pstmt.setString(4, vo.getPhone());
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) vo.setNo(rs.getLong(1));
            } catch (SQLException e) { e.printStackTrace(); }
        }

        public List<UserVo> findAll() {
            List<UserVo> list = new ArrayList<>();
            String sql = "SELECT no, name, email, password, phone FROM user";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    UserVo vo = new UserVo();
                    vo.setNo(rs.getLong("no"));
                    vo.setName(rs.getString("name"));
                    vo.setEmail(rs.getString("email"));
                    vo.setPassword(rs.getString("password"));
                    vo.setPhone(rs.getString("phone"));
                    list.add(vo);
                }
            } catch (SQLException e) { e.printStackTrace(); }
            return list;
        }

        public void deleteByNo(Long no) {
            String sql = "DELETE FROM user WHERE no=?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, no);
                pstmt.executeUpdate();
            } catch (SQLException e) { e.printStackTrace(); }
    }

}

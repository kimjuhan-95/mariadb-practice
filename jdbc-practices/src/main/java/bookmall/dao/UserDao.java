package bookmall.dao;

import java.sql.*;
import java.util.*;
import bookmall.vo.UserVo;

public class UserDao {
    public void insert(UserVo vo) {
        String sql = "insert into user(name, email, password, phone) values(?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getEmail());
            pstmt.setString(3, vo.getPassword());
            pstmt.setString(4, vo.getPhone());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    vo.setNo(rs.getLong(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserVo> findAll() {
        List<UserVo> list = new ArrayList<>();
        String sql = "select no, name, email, password, phone from user";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UserVo vo = new UserVo();
                vo.setNo(rs.getLong(1));
                vo.setName(rs.getString(2));
                vo.setEmail(rs.getString(3));
                vo.setPassword(rs.getString(4));
                vo.setPhone(rs.getString(5));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteByNo(Long no) {
        String sql = "delete from user where no = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, no);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/bookmall", "bookmall", "bookmall");
    }
}

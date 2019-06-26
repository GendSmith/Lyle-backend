package dao;

import bean.User;
import utils.DBUtil;

import java.sql.*;


public class UserDAO {
    public int getTotalUserNum(){
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()){
            String sql = "select count(*) from user";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                total = rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    public User get(int id){
        User bean = null;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()){
            String sql = "select * from user where id=" + id;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                bean = new User();
                bean.setId(id);
                bean.setUsername(rs.getString("username"));
                bean.setPassword(rs.getString("password"));
                bean.setAge(rs.getInt("age"));
                bean.setgender(rs.getString("gender"));
                bean.setProfilePic(rs.getString("profilePic"));
            }
        }catch (SQLException e) {

            e.printStackTrace();
        }
        return bean;
    }

    public void add(User bean) {

        String sql = "insert into user values(? ,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(0, bean.getUsername());
            ps.setString(1, bean.getPassword());
            ps.setString(2, bean.getProfilePic());
            ps.setString(3, bean.getjob());
            ps.setInt(4, bean.getAge());
            ps.setString(5, bean.getgender());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                bean.setId(id);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}

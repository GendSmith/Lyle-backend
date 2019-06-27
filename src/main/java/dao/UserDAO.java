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
            System.out.println(sql);
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

    public boolean add(User bean) {

        String sql = "insert into user(username,password,profilePic,job,age,gender) values(? ,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, bean.getUsername());
            ps.setString(2, bean.getPassword());
            ps.setString(3, bean.getProfilePic());
            ps.setString(4, bean.getjob());
            ps.setInt(5, bean.getAge());
            ps.setString(6, bean.getgender());

            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }
}

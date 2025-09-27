package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.iotstar.configs.dbconnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.User;

public class UserDaoImpl implements UserDao {

	dbconnection dbConn = new dbconnection();
    @Override
    public User get(String username) {
        String sql = "SELECT * FROM [User] WHERE username=?";
        try (Connection conn = dbConn.getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString("username"));
                u.setPassWord(rs.getString("password"));
                u.setFullName(rs.getString("fullname"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO [User](username, password, email, fullname, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbConn.getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFullName());
            ps.setString(5, user.getPhone());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        String sql = "SELECT 1 FROM [User] WHERE email=?";
        try (Connection conn =dbConn.getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String sql = "SELECT 1 FROM [User] WHERE username=?";
        try (Connection conn = dbConn.getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String sql = "SELECT 1 FROM [User] WHERE phone=?";
        try (Connection conn = dbConn.getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
package vn.iotstar.service.impl;

import vn.iotstar.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vn.iotstar.configs.dbconnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.User;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    dbconnection dbConn = new dbconnection();
    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        User newUser = new User(email, username, fullname, password, null, phone);
        userDao.insert(newUser);
        return true;
    }

    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        User user = userDao.get(email);
        if (user != null) {
            user.setPassWord(newPassword);
            userDao.insert(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE [User] SET fullname = ?, phone = ?, avatar = ? WHERE id = ?";
        try (Connection conn = dbConn.getConnectionW();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getAvatar());
            ps.setInt(4, user.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

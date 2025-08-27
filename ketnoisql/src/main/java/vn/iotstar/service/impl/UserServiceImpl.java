package vn.iotstar.service.impl;

import vn.iotstar.service.UserService;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();
	@Override
	public vn.iotstar.models.User login(String username, String password) {

		User user = this.get(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
			}
			return null;
	}

	@Override
	public vn.iotstar.models.User get(String username) {

		return userDao.get(username);
	}
}

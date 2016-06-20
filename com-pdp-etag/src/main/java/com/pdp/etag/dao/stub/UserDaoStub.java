package com.pdp.etag.dao.stub;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pdp.etag.dao.UserDao;
import com.pdp.etag.domain.User;

public class UserDaoStub implements UserDao {

	private static Map<Integer, User> users = new HashMap<Integer, User>();

	static {
		User user = new User();
		user.setId(1);
		user.setFirstName("demo");
		user.setLastName("user");
		user.setUri("/user-management/users/1");
		user.setLastModified(new Date());
		users.put(1, user);
	}

	@Override
	public User getById(Integer id) {
		return users.get(id);
	}

	@Override
	public void update(Integer id) {
		User user = users.get(id);
		user.setLastModified(new Date());
	}

	@Override
	public Date getLastModifiedById(Integer id) {
		return users.get(id).getLastModified();
	}

}

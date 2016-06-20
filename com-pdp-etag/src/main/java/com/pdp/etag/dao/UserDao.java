package com.pdp.etag.dao;

import java.util.Date;

import com.pdp.etag.domain.User;

public interface UserDao {
	 User getById(Integer id);
	 
	 void update(Integer id);
	 
	 Date getLastModifiedById(Integer id);
}

package com.surve_Food.service;

import com.surve_Food.Model.User;

public interface UserService {
	public User findUserByJwtToken(String jwt) throws Exception;

	public User findUserByEmail(String email) throws Exception;
}

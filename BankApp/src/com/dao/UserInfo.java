package com.dao;

import java.util.List;

import com.model.Account;
import com.model.User;

public interface UserInfo {
	
	void save(User user);
	void checkUser(User user);
	List<User> findUserbyname(String username);
	List<User> getUserData();
	void depositByAccount(Account account);

}
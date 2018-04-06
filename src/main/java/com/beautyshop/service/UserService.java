package com.beautyshop.service;

import com.beautyshop.entity.User;

public interface UserService {
	
	void saveService(User user); 
	
	User findUserByEmail(String email);
	
	User findUserById(int userId);
	
	void updateUser(User user);

}

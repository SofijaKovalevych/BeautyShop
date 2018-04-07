package com.beautyshop.service;

import com.beautyshop.entity.User;

public interface UserService {
	
	void saveService(User user); 
	
	User findUserByEmail(String email);
	
	User findUserById(int userId);
	
	void updateUser(User user);

	void saveAndEncode(User user);
	
	void addToShoppingCart(int userId, int itemId);

	void removeToShoppingCart(int userId, int itemId);
	
	void removeAllToShoppingCart(int userId);

	int createNewUser();
	
	void sendMail(String content, String victimName);

}

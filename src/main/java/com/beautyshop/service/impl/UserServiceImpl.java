package com.beautyshop.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.beautyshop.entity.User;
import com.beautyshop.enumeration.Role;
import com.beautyshop.repository.UserRepository;
import com.beautyshop.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository; 
	
	private PasswordEncoder encoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	@Override
	public void saveService(User user) {
		userRepository.save(user);
		
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserById(int userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = userRepository.findByEmail(username);
		if (user == null) {
            throw new UsernameNotFoundException(String.format("Username '%s' not found", username));
        }
		return user;
	}	
	
	@PostConstruct
	private void createAdmin() {
		User user = userRepository.findByEmail("admin");
		if(user == null) {
			user = new User();
			user.setEmail("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			userRepository.save(user);
		}
	}

	@Override
	public void saveAndEncode(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}

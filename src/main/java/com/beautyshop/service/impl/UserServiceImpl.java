package com.beautyshop.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.beautyshop.entity.Item;
import com.beautyshop.entity.ShopingCart;
import com.beautyshop.entity.User;
import com.beautyshop.enumeration.Role;
import com.beautyshop.repository.ItemRepository;
import com.beautyshop.repository.ShopingCartRepository;
import com.beautyshop.repository.UserRepository;
import com.beautyshop.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	private UserRepository userRepository; 
	
	private PasswordEncoder encoder;
	
	private ItemRepository itemRepository;
	
	private ShopingCartRepository shopingCartRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, ItemRepository itemRepository,
			ShopingCartRepository shopingCartRepository) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.itemRepository = itemRepository;
		this.shopingCartRepository = shopingCartRepository;
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
		user.setRole(Role.ROLE_USER);
		userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void addToShoppingCart(int userId, int itemId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		if(cart==null){
			cart = shopingCartRepository.save(new ShopingCart());
			user.setShopingCart(cart);
		}
		Item item = itemRepository.findOne(itemId);
		cart.add(item);
	}
	
	@Override
	@Transactional
	public void removeToShoppingCart(int userId, int itemId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		Item item = itemRepository.findOne(itemId);
		cart.remove(item);
	}
	
	@Override
	@Transactional
	public void removeAllToShoppingCart(int userId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		List<Item> modelServo = itemRepository.findAll();
		cart.removeAll(modelServo);
	}

	@Override
	public int createNewUser() {
		return userRepository.saveAndFlush(new User()).getId();
	}
	
	@Override
	public void sendMail(String content, String email) {
		String mailBody = emailBody(email);
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"bobotishe@gmail.com", "qwe123asd");
					}
				});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bobotishe@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email));
			message.setSubject(content, "UTF-8");
			message.setText(mailBody);
			Transport.send(message);
		} catch (MessagingException е) {
			е.printStackTrace();
		}
	}

	private String emailBody(String email) {
		String item = "";
		BigDecimal fullPrice = new BigDecimal(0);
		List<Item> items = itemRepository.findAllByUserId(userRepository.findByEmail(email).getId());
		for(Item i: items) {
			item = item + i.getName() + "Prinse:" + i.getPrice().toString() + "\r";
			fullPrice = fullPrice.add(i.getPrice());
		}
		
		return item + "\r" + fullPrice;
	}
	
	
}

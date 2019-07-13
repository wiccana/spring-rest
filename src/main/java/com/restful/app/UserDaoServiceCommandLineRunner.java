package com.restful.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.restful.app.user.User;
import com.restful.app.user.UserRepository;
import com.restful.app.user.UserService;

@Component
public class UserDaoServiceCommandLineRunner implements CommandLineRunner {

	@Autowired
 	private UserRepository userRepository;
	
	@Autowired
	private	BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger log = LoggerFactory.getLogger(UserDaoServiceCommandLineRunner.class);
	
	
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setName("Sansa");
		user.setUsername("sansa");
		user.setPassword(bCryptPasswordEncoder.encode("1234"));
		userRepository.save(user);
		log.info("user created");
	}

}

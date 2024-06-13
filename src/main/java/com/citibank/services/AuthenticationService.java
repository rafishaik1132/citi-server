package com.citibank.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.citi.dto.LoginUserDto;
import com.citi.dto.RegisterUserDto;
import com.citibank.controllers.EmployeeController;
import com.citibank.model.User;
import com.citibank.repository.UserRepository;

@Service
public class AuthenticationService {
	
	Logger logger  = LogManager.getLogger(AuthenticationService.class);
	
	 private final UserRepository userRepository;
	    
	    private final PasswordEncoder passwordEncoder;
	    
	    private final AuthenticationManager authenticationManager;

	    public AuthenticationService(
	        UserRepository userRepository,
	        AuthenticationManager authenticationManager,
	        PasswordEncoder passwordEncoder
	    ) {
	        this.authenticationManager = authenticationManager;
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    public User signup(RegisterUserDto input) {
	        User user = new User();
	                user.setFullName(input.getFullName());
	                user.setEmail(input.getEmail());
	                user.setPassword(passwordEncoder.encode(input.getPassword()));

	        return userRepository.save(user);
	    }

	    public User authenticate(LoginUserDto input) {
	    	
	    	logger.info("inside authenticate method"+input);
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        input.getEmail(),
	                        input.getPassword()
	                )
	        );

	        return userRepository.findByEmail(input.getEmail())
	                .orElseThrow();
	    }
	}


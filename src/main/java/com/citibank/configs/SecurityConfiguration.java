package com.citibank.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	 Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);  
	
	
	 private final AuthenticationProvider authenticationProvider;
	    private final JwtAuthenticationFilter jwtAuthenticationFilter;

	    public SecurityConfiguration(
	        JwtAuthenticationFilter jwtAuthenticationFilter,
	        AuthenticationProvider authenticationProvider
	    ) {
	    	logger.info("inside SecurityConfiguration method");
	        this.authenticationProvider = authenticationProvider;
	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    }

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http.csrf()
	                .disable()
	                .authorizeHttpRequests()
	                .requestMatchers("/auth/**")
	                .permitAll()
	                .anyRequest()
	                .authenticated()
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	                .authenticationProvider(authenticationProvider)
	                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	    
	}





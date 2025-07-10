package com.javaweb.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.springframework.http.HttpMethod.*;
import com.javaweb.api.filter.JwtTokenFilter;

import lombok.RequiredArgsConstructor;



@Configuration 
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final JwtTokenFilter jwtTokenJwt;
	
	@Value("${api.prefix}")
    private String apiPrefix;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
				.csrf(AbstractHttpConfigurer :: disable)
				.addFilterBefore(jwtTokenJwt,UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(request -> {
					request
							.requestMatchers(
									String.format("%s/login",apiPrefix),
									String.format("%s/register",apiPrefix)
							).permitAll()
							.requestMatchers(GET ,
									String.format("%s/test",apiPrefix)).hasAnyRole("MANAGER")
							.anyRequest().authenticated();
				});
		
		return http.build();
	}
}

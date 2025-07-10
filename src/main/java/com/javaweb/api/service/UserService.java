package com.javaweb.api.service;

import org.springframework.http.ResponseEntity;

import com.javaweb.api.model.dto.UserDTO;

public interface UserService {
	String login(String userName , String password) throws Exception;
	void registerUser(UserDTO userDTO) throws Exception;
}

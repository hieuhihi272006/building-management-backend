package com.javaweb.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.api.model.dto.UserDTO;
import com.javaweb.api.model.dto.UserLoginDTO;
import com.javaweb.api.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/building")
public class UserAPI {

	@Autowired 
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO ,
											BindingResult result){
		try {
			if(result.hasErrors()) {
				List<String> errorMessage = result.getFieldErrors()
						.stream()
						.map(FieldError::getDefaultMessage)
						.toList();
				return ResponseEntity.badRequest().body(errorMessage);
			}
			if(!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
				return ResponseEntity.badRequest().body("Password not match");
			}
			userService.registerUser(userDTO);
			return ResponseEntity.ok("");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO){
		try {
			String token = userService.login(userLoginDTO.getUserName(),userLoginDTO.getPassword());
			return ResponseEntity.ok(token);
		}catch(Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}

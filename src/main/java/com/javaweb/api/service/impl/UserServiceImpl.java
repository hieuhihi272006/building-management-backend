package com.javaweb.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.javaweb.api.customexception.DataNotFoundException;
import com.javaweb.api.customexception.PermissionDenyException;
import com.javaweb.api.entity.RoleEntity;
import com.javaweb.api.entity.UserEntity;
import com.javaweb.api.model.dto.UserDTO;
import com.javaweb.api.repository.RoleRepository;
import com.javaweb.api.repository.UserRepository;
import com.javaweb.api.service.UserService;
import com.javaweb.api.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor    
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtTokenUtil jwtTokenUtil;
	private final RoleRepository roleRepository;
	
	@Override
	public String login(String userName, String password) throws Exception{
		// TODO Auto-generated method stub
		Optional<UserEntity> optionalUser = userRepository.findByName(userName);
		if(optionalUser.isEmpty()) {
			throw new DataNotFoundException("Invalid phone number/password");	
		}
			UserEntity existingUser = optionalUser.get();
			if(!passwordEncoder.matches(password, existingUser.getPassword())) {
				throw new BadCredentialsException("Wrong UserName or Password !");
			}
			UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(userName , password);
			Authentication authentication = authenticationManager.authenticate(authen);
			return jwtTokenUtil.generateToken(existingUser);
	}
	

	@Override
	public void registerUser(UserDTO userDTO) throws Exception{
		// TODO Auto-generated method stub
		String userName = userDTO.getUserName();
		if(userRepository.existsByName(userName)) {
			throw new DataIntegrityViolationException("UserName already exists");
		}
		List<RoleEntity> roleEntity = new ArrayList<>();
		RoleEntity role = roleRepository.findById(userDTO.getRoleId())
						.orElseThrow(() -> new DataNotFoundException("Role not found"));
		roleEntity.add(role);
		UserEntity newUser = UserEntity.builder()
				.fullname(userDTO.getFullName())
				.name(userDTO.getUserName())
				.email(userDTO.getMail())
				.status(1)
				.password(userDTO.getPassword())
				.build();
		newUser.setRoles(roleEntity);
		String password = userDTO.getPassword();
		String encoderPassword = passwordEncoder.encode(password);
		newUser.setPassword(encoderPassword);
		userRepository.save(newUser);
	}	

}

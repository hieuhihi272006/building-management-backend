package com.javaweb.api.utils;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.javaweb.api.customexception.InvalidParamException;
import com.javaweb.api.entity.RoleEntity;
import com.javaweb.api.entity.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

	@Value("${jwt.expiration}")
	private Long expiration;
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	private Key getSingInKey() {
		byte[] bytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(bytes);
	}
	
	public String generateToken(UserEntity userEntity) throws Exception{
		Map<String,Object> claims = new HashMap<>();
		claims.put("userName",userEntity.getUsername());
		List<String> roles = new ArrayList<>() ;
		for(RoleEntity it : userEntity.getRoles()) {
			if(it.getCode() != null) {
				roles.add(it.getCode());
			}
		}
		claims.put("roles", roles);
		try {
			String token = Jwts.builder()
					.setClaims(claims)
					.setSubject(userEntity.getUsername())
					.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
					.signWith(getSingInKey(),SignatureAlgorithm.HS256)
					.compact();
			return token;
		}catch(Exception e) {
			throw new InvalidParamException("Cannot create jwt token , error " + e.getMessage());
		}

	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
					.setSigningKey(getSingInKey())
					.build()	
					.parseClaimsJws(token)	
					.getBody();
	}
	
	public <T> T extractClaim(String token , Function<Claims , T> claimsResolver) {
		final Claims claims = this.extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public List<String> extractRoles(String token) {	
		Claims claims = extractAllClaims(token);
		Object roles = claims.get("roles");
		if(roles instanceof List<?>) {
			return ((List<?>) roles).stream()
					.map(Object :: toString)
					.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
	public boolean isTokenExpired(String token ) {
		Date expirationDate = this.extractClaim(token, Claims :: getExpiration);
		return expirationDate.before(new Date());
	}
	public boolean validateToken(String token , UserDetails userDetails) {
		String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

}

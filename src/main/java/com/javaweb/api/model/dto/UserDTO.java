package com.javaweb.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	@JsonProperty("user_name")
	private String userName;
	
	@JsonProperty("phone_number")
	@NotBlank(message = "Phone number is required")
	private String phoneNumber;
	
	@JsonProperty("full_name")
	private String fullName;
	
	@JsonProperty("mail")
	private String mail;

	@NotBlank(message = "Password can not be blank")
	private String password;
	
	@JsonProperty("retype_password")
    private String retypePassword;
	
	@NotNull(message = "Role id is required")
    @JsonProperty("role_id")
    private Long roleId;
	

}

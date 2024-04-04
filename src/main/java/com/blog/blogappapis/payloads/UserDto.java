package com.blog.blogappapis.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	 @NotBlank(message = "Name is required")
	private String name;
	  @Email(message = "Invalid email address")
	private String email;
	 @NotBlank
	private String password;
	 @NotBlank
	private String about;

}

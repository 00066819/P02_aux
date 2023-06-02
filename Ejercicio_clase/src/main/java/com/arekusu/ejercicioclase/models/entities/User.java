package com.arekusu.ejercicioclase.models.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	
	@Column(name = "code")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "username")
	@NotEmpty
	@Size(min = 4, max = 15)
	private String username;
	
	
	@Column(name = "email")
	@Email
	private String email;
	
	@Column(name = "password")
	@NotEmpty
	@Pattern(regexp = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$")
	private String password;

	public User(@NotEmpty @Size(min = 4, max = 15) String username, @Email String email,
			@NotEmpty @Size(min = 8, max = 16) String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}

}

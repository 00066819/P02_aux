package com.arekusu.ejercicioclase.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginDTO {
	@NotEmpty
	private String identifier;
	
	@NotEmpty
	private String password;

}

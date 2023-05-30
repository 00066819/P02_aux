package com.arekusu.ejercicioclase.models.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SavePlaylistDTO {
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	@Pattern(regexp = "^[0-9A-Z]{4}$")
	private String userCode;
	

}

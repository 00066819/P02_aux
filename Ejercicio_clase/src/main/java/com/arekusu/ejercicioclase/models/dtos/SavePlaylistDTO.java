package com.arekusu.ejercicioclase.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class SavePlaylistDTO {
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private UUID userCode;
	

}

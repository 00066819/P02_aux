package com.arekusu.ejercicioclase.models.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="song")
public class Song {
	
	@Id
	@Column(name="code")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "name")
	@NotEmpty
	private String name;
	private int duration;

}

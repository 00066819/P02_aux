package com.arekusu.ejercicioclase.models.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@Column(name = "title")
	@NotEmpty
	private String name;
	
	@Column(name = "duration")
	@NotEmpty
	private int duration;
	

	public Song(@NotEmpty String name, @NotEmpty int duration) {
		super();
		this.name = name;
		this.duration = duration;
	}
	
	

}

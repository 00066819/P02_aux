package com.arekusu.ejercicioclase.models.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="playlist")
@NoArgsConstructor
@Data
@Entity
public class Playlist {
	
	@Id
	@Column(name = "code")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID code;
	
	@Column(name = "title")
	@NotEmpty
	@Size(min = 5, max = 15)
	private String title;
	
	@Column(name = "description")
	@NotEmpty
	@Size(min = 10, max = 100)
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_code", nullable = true)
	@JsonIgnore
	private User user;

	public Playlist(@NotEmpty @Size(min = 5, max = 15) String title,
			@NotEmpty @Size(min = 10, max = 100) String description) {
		super();
		this.title = title;
		this.description = description;
	}
	
}

package com.arekusu.ejercicioclase.models.entities;

import java.util.List;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name="Playlist")
@ToString(exclude = {"songs"})
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
	
	@Column(name = "user_code")
	@NotEmpty
	private String userCode;
	
	@OneToMany(mappedBy = "playlist")
    private List<Song> songs;

	public Playlist(@NotEmpty @Size(min = 5, max = 15) String title,
			@NotEmpty @Size(min = 10, max = 100) String description) {
		super();
		this.title = title;
		this.description = description;
	}
}
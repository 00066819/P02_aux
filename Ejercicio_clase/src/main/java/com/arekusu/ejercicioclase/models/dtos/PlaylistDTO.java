package com.arekusu.ejercicioclase.models.dtos;

import java.util.List;
import java.util.UUID;

import com.arekusu.ejercicioclase.models.entities.Song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
	private UUID code;
	private String title;
	private String description;
	private List<Song> songs;
	private int totalDuration;
}

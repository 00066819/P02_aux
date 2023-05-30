package com.arekusu.ejercicioclase.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.arekusu.ejercicioclase.models.entities.Playlist;

@Repository
public interface PlaylistRepository extends ListCrudRepository<Playlist, UUID> {
	
	List<Playlist> findById(String Id);

	void deleteByTitle(String title);

}

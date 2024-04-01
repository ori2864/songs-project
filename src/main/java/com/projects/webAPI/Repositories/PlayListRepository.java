package com.projects.webAPI.Repositories;

import com.projects.webAPI.Beans.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<Playlist,Integer> {
}

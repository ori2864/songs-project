package com.projects.webAPI.Repositories;

import com.projects.webAPI.Beans.YoutubeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<YoutubeData,String> {
    boolean existsByName(String name);
}

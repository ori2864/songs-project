package Repositories;

import com.projects.webAPI.Beans.Playlist;
import com.projects.webAPI.Beans.YoutubeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<Playlist,Integer> {
}

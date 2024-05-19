package com.projects.webAPI.Services;

import com.projects.webAPI.Beans.YoutubeData;
import com.projects.webAPI.Repositories.PlayListRepository;
import com.projects.webAPI.Repositories.SongRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.projects.webAPI.Beans.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor

public class PlaylistService {
    private final RestTemplate restTemplate;
    private final SongRepository dataRepo;
    private final PlayListRepository playListRepo;
    private final SongService songService;
    private String URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyB_rfFikBTjch9lqoaaLuqX_ETqsGGTvN8&id=";

    public Boolean createPlayList(String name) throws JsonProcessingException {
        //creating playlist and injecting the name
        if (dataRepo.existsByName(name)) {

            System.out.println("this playlist already exists");
            return false;
        }
        Playlist playlist = Playlist.builder().name(name).build();

        /*     *disabled* adding songs by id iteration
        for (String id : ids) {
            YoutubeData songData=findSong(id);
            playlist.getSongs().add(songData);
        }    */


        //saving the new playlist
        playListRepo.save(playlist);
        return true;
    }

    public Boolean addSongToPlaylist(Integer listId, String songId) throws JsonProcessingException {
        if (playListRepo.findById(listId).isPresent()) {
            Playlist playlist = playListRepo.findById(listId).get();
            playlist.getSongs().add(songService.findSong(songId));
            playListRepo.saveAndFlush(playlist);
            return true;
        } else System.out.println("no such playlist my friend....");
        return false;
    }

    public Boolean deleteSongFromPlaylist(Integer listId, String songId) throws JsonProcessingException {
        if (playListRepo.findById(listId).isPresent()) {
            Playlist playlist = playListRepo.findById(listId).get();
            playlist.getSongs().remove(songService.findSong(songId));
            playListRepo.saveAndFlush(playlist);
            return true;
        } else System.out.println("no such playlist my friend....");
        return false;
    }

    public Boolean clearSongsFromPlaylist(Integer listId) {
        if (playListRepo.findById(listId).isPresent()) {
            Playlist playlist = playListRepo.findById(listId).get();
            playlist.getSongs().clear();
            playListRepo.saveAndFlush(playlist);
            return true;
        } else System.out.println("no such playlist my friend....");
        return false;
    }
    public List<YoutubeData>getAllSongs(){
        return dataRepo.findAll();
    }
}

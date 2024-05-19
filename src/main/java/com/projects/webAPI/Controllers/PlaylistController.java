package com.projects.webAPI.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.projects.webAPI.Beans.Playlist;
import com.projects.webAPI.Beans.YoutubeData;
import com.projects.webAPI.Services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
@CrossOrigin
public class PlaylistController {
    private final PlaylistService playlistService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean createPlaylist(String name) throws JsonProcessingException {
        return playlistService.createPlayList(name);
    }
    @PostMapping("/add/{listId}/{songId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean addSongToPlaylist(@PathVariable Integer listId, @PathVariable String songId) throws JsonProcessingException {
        return playlistService.addSongToPlaylist(listId, songId);
    }

    @PutMapping("/delete/{listId}/{songId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean deleteSongFromPlaylist(@PathVariable Integer listId,@PathVariable String songId) throws JsonProcessingException {
        return playlistService.deleteSongFromPlaylist(listId, songId);
    }

    @PutMapping("/clear/{listId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean clearSongsFromPlaylist(@PathVariable Integer listId){
        return playlistService.clearSongsFromPlaylist(listId);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<YoutubeData> getAllSongs(){
        return playlistService.getAllSongs();
    }




}

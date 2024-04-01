package com.projects.webAPI.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.projects.webAPI.Beans.Playlist;
import com.projects.webAPI.Services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    @PostMapping("/create/playlist")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean createPlaylist(String name) throws JsonProcessingException {
        return playlistService.createPlayList(name);
    }
    @PostMapping("/add/song/{listId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean addSongtoPlaylist(@PathVariable Integer listId, String songId) throws JsonProcessingException {
        return playlistService.addSongToPlaylist(listId, songId);
    }

    @PutMapping("/delete/song/{listId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean deleteSongFromPlaylist(@PathVariable Integer listId, String songId) throws JsonProcessingException {
        return playlistService.deleteSongFromPlaylist(listId, songId);
    }

    @PutMapping("/clear/playlist/{listId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean clearSongsFromPlaylist(@PathVariable Integer listId){
        return playlistService.clearSongsFromPlaylist(listId);
    }




}

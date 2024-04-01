package com.projects.webAPI.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projects.webAPI.Beans.YoutubeData;
import com.projects.webAPI.Services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {
private final SongService songService;

@GetMapping("/get/song/{id}")
@ResponseStatus(HttpStatus.OK)
public YoutubeData findSong(@PathVariable String id) throws JsonProcessingException {
    return songService.findSong(id);
}
//i dont want the client to have the other function because it is for internal use...


}

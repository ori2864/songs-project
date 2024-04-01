package com.projects.webAPI.clr;

import com.projects.webAPI.Services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class TestAPI implements CommandLineRunner {

    private final SongService songService;


    @Override
    public void run(String... args) throws Exception {
        songService.getYoutubeData("4m1EFMoRFvY");

    }
}

package com.projects.webAPI.clr;

import com.projects.webAPI.Services.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestAPI implements CommandLineRunner {

    private final DataService dataService;


    @Override
    public void run(String... args) throws Exception {
        dataService.getYoutubeData("4m1EFMoRFvY");

    }
}

package com.projects.webAPI.Services;

import com.projects.webAPI.Beans.UserDetails;
import com.projects.webAPI.Repositories.PlayListRepository;
import com.projects.webAPI.Repositories.SongRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.webAPI.Beans.YoutubeData;
import com.projects.webAPI.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor


public class SongService {
    //    @Value("${youtube.api}")
//    private String API_KEY;
    private final RestTemplate restTemplate;
    private final SongRepository dataRepo;
    private final PlayListRepository playListRepo;
    private String URL = "https://www.googleapis.com/youtube/v3/videos?part=snippet&key=AIzaSyB_rfFikBTjch9lqoaaLuqX_ETqsGGTvN8&id=";


    public YoutubeData getYoutubeData(String id) throws JsonProcessingException {
        //to get data for song id WeYsTmIzjkw
        //get all meta data from google as string
        String myMetaData = restTemplate.getForObject(URL + id, String.class);
        //       gh auth l
        //use object mapper , to map our item in the json object
        ObjectMapper mapper = new ObjectMapper();
        //get snippet data, by using items field, and since it's array, get first data (0)
        var myData = mapper.readTree(myMetaData).get("items").get(0).get("snippet");
        //create new bean of YouTubeData -> name,description, image
        YoutubeData youtubeData = YoutubeData.builder()
                .id(id)
                .name(myData.get("title").asText())
                .description(myData.get("description").asText())
                .imageURL(myData.get("thumbnails").get("standard").get("url").asText())
                .build();
        String desc = myData.get("description").asText();
        youtubeData.setDescription(desc.length() > 200 ? desc.substring(0, 200) : desc);

        System.out.println(youtubeData);
        if (dataRepo.findById(youtubeData.getId()).isEmpty()) {
            dataRepo.save(youtubeData);
        }
        return youtubeData;
    }

    public YoutubeData findSong(String id) throws JsonProcessingException {
        //check if we have the data already
        //return youtubeRepo.findById(id).orElse(getYouTubeData(id));
        if (dataRepo.findById(id).isPresent()) {
            System.out.println("we have the song...");
            return dataRepo.findById(id).get();
        } else {
            System.out.println("we don't have the song....");
            return this.getYoutubeData(id);
        }
    }




}

package com.projects.webAPI.Services;

import Repositories.DataRepository;
import Repositories.PlayListRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projects.webAPI.Beans.Playlist;
import com.projects.webAPI.Beans.YoutubeData;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Data
@RequiredArgsConstructor


public class DataService {
    //    @Value("${youtube.api}")
//    private String API_KEY;
    private final RestTemplate restTemplate;
    private final DataRepository dataRepo;
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

    public void addSongToPlaylist(Integer listId, String songId) throws JsonProcessingException {
        if (playListRepo.findById(listId).isPresent()) {
            Playlist playlist = playListRepo.findById(listId).get();
            playlist.getSongs().add(findSong(songId));
            playListRepo.saveAndFlush(playlist);
        } else System.out.println("no such playlist my friend....");
    }

    public void deleteSongFromPlaylist(Integer listId, String songId) throws JsonProcessingException {
        if (playListRepo.findById(listId).isPresent()) {
            Playlist playlist = playListRepo.findById(listId).get();
            playlist.getSongs().remove(findSong(songId));
            playListRepo.saveAndFlush(playlist);
        } else System.out.println("no such playlist my friend....");
    }

    public void clearSongsFromPlaylist(Integer listId) {
        if (playListRepo.findById(listId).isPresent()) {
            Playlist playlist = playListRepo.findById(listId).get();
            playlist.getSongs().clear();
            playListRepo.saveAndFlush(playlist);
        } else System.out.println("no such playlist my friend....");
    }

}

package com.projects.webAPI.Beans;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class YoutubeData {
    @Id
    private String id;
    private String name;
    private String description;
    private String imageURL;

}

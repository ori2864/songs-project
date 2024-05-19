package com.projects.webAPI.Beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany @JoinTable(
            inverseJoinColumns = @JoinColumn(unique = true)
    )
    private List<YoutubeData> songs;

}

package mcrmilenial.appsebookViewerbackend.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bibliographic")
public class Bibliographic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int biblion_id;
    private String title;
    private String author;
    private String abstrack;
    private String edition;
    private String no_register;
    private String publisher;
    private String publisher_year;
    private String publisher_place;
    private String subjek;
    private String image;
    private String file;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

}

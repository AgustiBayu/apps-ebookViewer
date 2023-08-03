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
    private String statement_of_resconsibility;
    private String edition;
    private String specific_detail_info;
    private String item_code_batch_generator;
    private String gmd;
    private String content_type;
    private String media_type;
    private String carrier_type;
    private String frequency;
    private String isbn_issn;
    private String publisher;
    private String publisher_year;
    private String publisher_place;

//  private String collation;
    private String image;
    private String file;
    private String related_biblio_data;
    private String hide_in_opac;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

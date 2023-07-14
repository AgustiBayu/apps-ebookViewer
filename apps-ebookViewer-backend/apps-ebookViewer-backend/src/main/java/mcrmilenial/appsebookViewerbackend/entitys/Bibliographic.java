package mcrmilenial.appsebookViewerbackend.entitys;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.io.Serializable;
@Entity
@Data
public class Bibliographic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Biblion_id;
    private String Title;
    private String Author;
    private String StatementOfReconsbility;
    private String Edition;
    private String SpesificDetailInfo;
    private String ItemBatchGenerator;
    private String GMD;
    private String ContentType;
    private String MediaType;
    private String CarrierType;
    private String Frequency;
    private String Isbn_Issn;
    private String Publisher;
    private String PublisherYear;
    private String PublisherPlace;
    private File Image;
    private String FileAttachment;
    private String RelotedBiblioData;
    private String HitOnOpac;
}

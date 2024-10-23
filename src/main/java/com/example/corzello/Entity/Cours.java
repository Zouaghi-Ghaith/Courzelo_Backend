package com.example.corzello.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cours implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCours;

    private String nomCour;

    private String detailsCour;

    private String courfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private ModuleEntity module1;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedAt;


    @ManyToOne(cascade = CascadeType.ALL)
    private ModuleEntity module;
    @ManyToOne(cascade = CascadeType.ALL)
    private Compte_rendu compteRendu;
    @ManyToOne(cascade = CascadeType.ALL)
    private Prog_educatif progEducatif;



}

package com.example.corzello.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prog_educatif implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProgEducatif;

    private String NomProg;
    private String DescriptionProg;


    @ManyToOne(cascade = CascadeType.ALL)
    private Universite universite;

    @OneToMany(mappedBy = "progEducatif",cascade = CascadeType.ALL)

    private Set<ModuleEntity>modules;

    @OneToMany(mappedBy = "progEducatif",cascade = CascadeType.ALL)

    private Set<Cours>cours;

}

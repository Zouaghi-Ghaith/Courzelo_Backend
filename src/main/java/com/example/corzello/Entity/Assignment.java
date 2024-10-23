package com.example.corzello.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Assignment implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idassignment;
    private String question;
    @OneToMany(mappedBy = "assignment",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    List<AssignmentEtudiant> etudiants ;
    @ManyToOne
    @JsonIgnore
    Prof prof;

}

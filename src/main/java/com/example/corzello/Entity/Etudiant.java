package com.example.corzello.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idetudiant;
    private String nom;
    private String email;

    @ManyToOne
    @JoinColumn(name = "idclass")
    @JsonBackReference
    private classManagement classM;
    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<AssignmentEtudiant> assignmentEtudiant ;

    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.ALL)
    private Set<Recruitement_process_details> recruitementprocessDetails;


   }

package com.example.corzello.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
public class AssignmentEtudiant implements Serializable {
    @EmbeddedId
    AssignmentEtudiantId id;

    @ManyToOne
    @MapsId("idassignment")
    @JoinColumn(name = "idassignment")
            @JsonIgnore
    Assignment assignment;

    @ManyToOne
    @MapsId("idetudiant")
    @JoinColumn(name = "idetudiant")
    Etudiant etudiant;

    float grade =-1;

    String reponse;
}

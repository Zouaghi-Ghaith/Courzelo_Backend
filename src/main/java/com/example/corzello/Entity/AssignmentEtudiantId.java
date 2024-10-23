package com.example.corzello.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentEtudiantId implements Serializable {
    @Column(name = "idassignment")
    int idassignment;

    @Column(name = "idetudiant")
    int idetudiant;
}

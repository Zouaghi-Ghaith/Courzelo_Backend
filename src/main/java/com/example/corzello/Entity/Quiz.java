package com.example.corzello.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuiz;
    private String name;
    private float score;
    private boolean passed;
    private int niveau;

    @JsonIgnore
    @OneToMany(mappedBy ="quiz", cascade = CascadeType.ALL)
    private Set<Question> questions;
    @JoinColumn(name = "idModule")
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private ModuleEntity moduleEntity;
    @ManyToOne
    @JoinColumn(name = "user_id") // Colonne pour stocker l'ID de l'utilisateur
    private UserEntity user;

}
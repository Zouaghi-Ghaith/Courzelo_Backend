package com.example.corzello.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuestion;

    private String question;
    private String response1;
    private String response2;
    private String response3;
    private String response4;
    private String correct;
    private float note;

    @JoinColumn(name = "idQuiz")
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Quiz quiz;
    @JsonIgnore
    @OneToOne(mappedBy ="question", cascade = CascadeType.ALL)
    private reponses_etudiant reponsesEtudiant;




}

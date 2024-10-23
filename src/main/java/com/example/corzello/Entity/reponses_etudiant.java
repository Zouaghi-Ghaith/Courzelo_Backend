package com.example.corzello.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class reponses_etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Long idResponsesEtudiant;

        private String responseEtudiant;

        private boolean result;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Etudiant etudiant;

    @OneToOne(cascade = CascadeType.ALL)
    private Question question;

}

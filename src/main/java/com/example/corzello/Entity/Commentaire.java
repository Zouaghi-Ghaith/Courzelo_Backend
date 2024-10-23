package com.example.corzello.Entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCommentaire;

    @ManyToOne
    @JoinColumn(name = "publication_id_publication")
    @JsonIgnore
    private Publication publication;



    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;


    @ManyToOne
    @JoinColumn(name = "user_id") // Colonne pour stocker l'ID de l'utilisateur
    private UserEntity user;

}
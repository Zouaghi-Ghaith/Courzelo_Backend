package com.example.corzello.Entity;

import com.example.corzello.Entity.Publication;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVote;

    private boolean upvoted;

    private boolean downvoted;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
}

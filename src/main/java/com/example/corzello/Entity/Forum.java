package com.example.corzello.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idForum;

    @OneToMany(mappedBy = "forum",cascade = CascadeType.ALL)
    private Set<Publication> publications;
    @ManyToMany(cascade = CascadeType.ALL)
    private  Set<UserEntity> users;

}

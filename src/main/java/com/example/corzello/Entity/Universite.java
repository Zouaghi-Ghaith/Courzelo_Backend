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
public class Universite implements AssociatedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;
    @OneToMany(mappedBy = "universite", cascade = CascadeType.ALL)
    private Set<Prog_educatif> progEducatifs;
}

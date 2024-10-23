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
public class Compte_rendu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCRendu;


    @OneToMany(mappedBy = "compteRendu",cascade = CascadeType.ALL)
    private Set<Cours> cours;



   
}

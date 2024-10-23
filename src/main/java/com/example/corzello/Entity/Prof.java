package com.example.corzello.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prof implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int idprof;
    private String nom;

  @OneToMany(mappedBy = "prof",cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ModuleEntity> moduleEntities ;

    @ManyToOne
    @JoinColumn(name = "idclass")
    @JsonBackReference
    private classManagement classMa;

    @OneToMany(mappedBy = "prof",cascade = CascadeType.ALL, orphanRemoval = true)
    List<Assignment> assignments;

}

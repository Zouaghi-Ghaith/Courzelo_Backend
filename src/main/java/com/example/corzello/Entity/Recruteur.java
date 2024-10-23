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
public class Recruteur implements AssociatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;

    @ManyToMany( mappedBy = "recruteurs",cascade = CascadeType.ALL)
    private Set<proposed_job_offer> proposedJobOffers;
    @OneToMany( mappedBy = "recruteur",cascade = CascadeType.ALL)
    private Set<Recruitement_process_details> recruitementprocessDetails;

}

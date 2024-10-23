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
public class proposed_job_offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJobOffer;
    private String Title;
    private String job_description;
    private String profile_description;
    private String entreprise_decription;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Recruteur> recruteurs;
    @OneToMany(mappedBy = "proposedJobOffer",cascade = CascadeType.ALL)
    private Set<Recruitement_process_details> recruitementprocessDetails;

}

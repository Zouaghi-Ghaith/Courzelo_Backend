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
public class Recruitement_process_details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRecrutement_PDetails;
    private String job_title;
    private Date start_date;
    private Date end_date;
    private Type_Status type_status;

    private String job_description;
    private String profile_description;
    private String entreprise_decription;
    private float percentage;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private proposed_job_offer proposedJobOffer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Recruteur recruteur;

    @Override
    public String toString() {
        return "Recruitement_process_details{" +
                "idRecrutement_PDetails=" + idRecrutement_PDetails +
                ", job_title='" + job_title + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", type_status=" + type_status +
                ", proposedJobOffer=" + proposedJobOffer +
                ", etudiant=" + etudiant +
                ", recruteur=" + recruteur +
                '}';
    }
}

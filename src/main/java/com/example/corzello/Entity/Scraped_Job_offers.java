package com.example.corzello.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Scraped_Job_offers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idScrappedOffer;

}

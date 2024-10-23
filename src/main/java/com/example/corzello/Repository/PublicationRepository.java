package com.example.corzello.Repository;

import com.example.corzello.Entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

}

package com.example.corzello.Repository;

import com.example.corzello.Entity.Etudiant;
import com.example.corzello.Entity.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UniversiteRepository extends JpaRepository<Universite,Long> {
}

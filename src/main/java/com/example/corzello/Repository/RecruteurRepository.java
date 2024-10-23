package com.example.corzello.Repository;

import com.example.corzello.Entity.Recruteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RecruteurRepository extends JpaRepository<Recruteur,Long> {
}

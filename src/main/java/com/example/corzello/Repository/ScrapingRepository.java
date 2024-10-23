package com.example.corzello.Repository;
import com.example.corzello.Entity.proposed_job_offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScrapingRepository extends JpaRepository<proposed_job_offer,Long> {
    @Query(value = "SELECT DISTINCT p.* FROM proposed_job_offer p " +
            "LEFT JOIN prog_educatif pe ON p.Title LIKE CONCAT('%', pe.nom_prog, '%') " +
            "LEFT JOIN module_entity m ON p.Title LIKE CONCAT('%', m.nom_module, '%') " +
            "LEFT JOIN universite u ON pe.universite_id_user = u.id_user " +
            "LEFT JOIN users user ON user.id = :userId " +
            "WHERE u.id_user = :userId OR user.id = :userId", nativeQuery = true)
    List<proposed_job_offer> getJobOffersForUser(@Param("userId") long userId);


}

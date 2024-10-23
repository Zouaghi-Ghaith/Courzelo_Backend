package com.example.corzello.Repository;

import com.example.corzello.Entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    List<Commentaire> findByPublicationIdPublication(Long id);
    int countByPublicationIdPublication(Long idPublication);
    int countByUserId(Long userId);

}

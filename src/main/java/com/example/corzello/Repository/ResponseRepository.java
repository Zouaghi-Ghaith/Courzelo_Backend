package com.example.corzello.Repository;

import com.example.corzello.Entity.Question;
import com.example.corzello.Entity.reponses_etudiant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends CrudRepository<reponses_etudiant, Long> {
    reponses_etudiant findByQuestion(Question question);
}

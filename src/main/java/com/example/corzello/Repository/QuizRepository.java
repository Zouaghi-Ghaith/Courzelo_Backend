package com.example.corzello.Repository;

import com.example.corzello.Entity.Prog_educatif;
import com.example.corzello.Entity.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends CrudRepository<Quiz, Long> {
}

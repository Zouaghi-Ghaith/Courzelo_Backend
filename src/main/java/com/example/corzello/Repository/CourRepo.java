package com.example.corzello.Repository;

import com.example.corzello.Entity.Cours;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourRepo extends JpaRepository<Cours,Long> {

    @Query("SELECT c FROM Cours c JOIN FETCH c.module")
    List<Cours> findAllCoursWithModules();

}

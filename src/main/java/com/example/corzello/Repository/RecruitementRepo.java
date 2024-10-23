package com.example.corzello.Repository;

import com.example.corzello.Entity.Recruitement_process_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface RecruitementRepo extends JpaRepository<Recruitement_process_details,Long> {
    @Query("SELECT r FROM Recruitement_process_details  r JOIN FETCH r.user")
    Set<Recruitement_process_details> findAllWithUserEntity();



}

package com.example.corzello.Repository;


import com.example.corzello.Entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssignmentrepo extends JpaRepository<Assignment,Integer> {
}

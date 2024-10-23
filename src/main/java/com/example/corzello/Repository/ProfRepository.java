package com.example.corzello.Repository;

import com.example.corzello.Entity.Prof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository

public interface ProfRepository extends JpaRepository<Prof,Long> {


}

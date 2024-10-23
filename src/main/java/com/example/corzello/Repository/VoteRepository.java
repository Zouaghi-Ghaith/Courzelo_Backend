package com.example.corzello.Repository;

import com.example.corzello.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    // Add custom query methods if needed
}

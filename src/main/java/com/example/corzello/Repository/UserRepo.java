package com.example.corzello.Repository;

import com.example.corzello.Entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email) ;
    UserEntity findById(long id) ;
}

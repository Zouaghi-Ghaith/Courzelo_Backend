package com.example.corzello.Repository;

import com.example.corzello.Entity.ModuleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ModuleRepository extends CrudRepository<ModuleEntity, Long> {

    @Override
    Optional<ModuleEntity> findById(Long IdProg);

    @Query("SELECT m FROM ModuleEntity m JOIN FETCH m.cours")
    Set<Module> findAllModulesWithCourses();
}

package com.example.corzello.Repository;

import com.example.corzello.Entity.ModuleEntity;
import com.example.corzello.Entity.Prog_educatif;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository  extends CrudRepository<Prog_educatif, Long> {

}

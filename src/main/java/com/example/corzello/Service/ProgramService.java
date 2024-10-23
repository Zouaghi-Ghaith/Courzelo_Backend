package com.example.corzello.Service;

import com.example.corzello.Entity.Prog_educatif;

import java.util.List;
import java.util.Optional;

public interface ProgramService {


    public Optional<Prog_educatif> getProgramById(Long id);
    public Prog_educatif saveOrUpdateProgram(Prog_educatif program);

    public void deleteProgram(Long id);
    public List<Prog_educatif> getAllProgram();

}

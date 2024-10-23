package com.example.corzello.Service;

import com.example.corzello.Entity.Prog_educatif;
import com.example.corzello.Repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramServiceImpl implements ProgramService {

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }


    @Autowired
    private ProgramRepository programRepository;


    public Optional<Prog_educatif> getProgramById(Long id) {
        return programRepository.findById(id);
    }


    public Prog_educatif saveOrUpdateProgram(Prog_educatif program) {
        return programRepository.save(program);
    }


    public void deleteProgram(Long id) {
        programRepository.deleteById(id);
    }


    public List<Prog_educatif> getAllProgram() {
        return (List<Prog_educatif>) programRepository.findAll();
    }




}

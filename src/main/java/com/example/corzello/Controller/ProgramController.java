package com.example.corzello.Controller;

import com.example.corzello.Entity.Prog_educatif;
import com.example.corzello.Service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Program")
@RequiredArgsConstructor
public class ProgramController implements Serializable {

    @Autowired
    private ProgramService programService;

    @GetMapping("/getAll")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Prog_educatif> getAllPrograms() {
        return programService.getAllProgram();
    }




    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Prog_educatif> createProgram(@RequestBody Prog_educatif Program) {
        Prog_educatif savedProgram = programService.saveOrUpdateProgram(Program);
        return new ResponseEntity<>(savedProgram, HttpStatus.CREATED);
    }



    @PutMapping("/update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Prog_educatif> updateProgram(@PathVariable Long id, @RequestBody Prog_educatif program) {
        program.setIdProgEducatif(id);
        Prog_educatif updateProgram = programService.saveOrUpdateProgram(program);
        return ResponseEntity.ok(updateProgram);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        programService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Prog_educatif> getProgramById(@PathVariable Long id) {
        Optional<Prog_educatif> programOptional = programService.getProgramById(id);
        return programOptional.map(program -> new ResponseEntity<>(program, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}


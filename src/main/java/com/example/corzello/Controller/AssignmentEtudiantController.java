package com.example.corzello.Controller;


import com.example.corzello.Entity.AssignmentEtudiant;
import com.example.corzello.Service.IAssignmentEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

public class AssignmentEtudiantController {

    private IAssignmentEtudiantService assignmentEtudiantService;
    @PostMapping("/assignment/{idAssignment}/etudiant/{idEtudiant}")
    public AssignmentEtudiant addAssignmentEtudiant(@RequestBody AssignmentEtudiant assignmentEtudiant, @PathVariable int idAssignment, @PathVariable int idEtudiant) {
        return assignmentEtudiantService.addAssignmentEtudiant( assignmentEtudiant,  idAssignment,  idEtudiant);
    }

    public void delete(int idAssignment, int idEtudiant) {

    }
}

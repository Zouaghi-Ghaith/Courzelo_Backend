package com.example.corzello.Service;


import com.example.corzello.Entity.Assignment;
import com.example.corzello.Entity.AssignmentEtudiant;
import com.example.corzello.Entity.AssignmentEtudiantId;
import com.example.corzello.Entity.Etudiant;
import com.example.corzello.Repository.AssignmentEtudiantRepo;
import com.example.corzello.Repository.IAssignmentrepo;
import com.example.corzello.Repository.Ietudiantrepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AssignmentEtudiantService implements IAssignmentEtudiantService{
    private AssignmentEtudiantRepo assignmentEtudiantRepo;
    private IAssignmentrepo assignmentrepo;
    private Ietudiantrepository etudiantrepository;
    @Override
    public AssignmentEtudiant addAssignmentEtudiant(AssignmentEtudiant assignmentEtudiant, int idAssignment, int idEtudiant) {
        Assignment a =new Assignment();
        a.setIdassignment(idAssignment);
        Etudiant e = new Etudiant();
        e.setIdetudiant(idEtudiant);
        assignmentEtudiant.setEtudiant(e);
        assignmentEtudiant.setAssignment(a);
        assignmentEtudiant.setId(new AssignmentEtudiantId(idAssignment,idEtudiant));
        return assignmentEtudiantRepo.save(assignmentEtudiant);
    }

    @Override
    public void delete(int idAssignment, int idEtudiant) {

    }
}

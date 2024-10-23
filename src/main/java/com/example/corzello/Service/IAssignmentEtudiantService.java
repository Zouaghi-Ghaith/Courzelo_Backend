package com.example.corzello.Service;


import com.example.corzello.Entity.AssignmentEtudiant;

public interface IAssignmentEtudiantService {

    AssignmentEtudiant addAssignmentEtudiant(AssignmentEtudiant assignmentEtudiant, int idAssignment, int idEtudiant) ;

    void delete(int idAssignment, int idEtudiant);
}

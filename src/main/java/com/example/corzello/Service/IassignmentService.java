package com.example.corzello.Service;



import com.example.corzello.Entity.Assignment;

import java.util.List;
import java.util.Optional;

public interface IassignmentService {

    public List<Assignment> getAll();
    public Assignment ajassignment(Assignment a );
    public List<Assignment> retrieveAll();
    public void remove(int idassignment);
    public Assignment update(int id,Assignment a);

    List<Assignment> getAssignmentEtudiant(int idassignment);
    public Optional<Assignment> getAssign(int id );

}

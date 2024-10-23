package com.example.corzello.Service;


import com.example.corzello.Entity.Assignment;
import com.example.corzello.Repository.IAssignmentrepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Slf4j
@Service
public class AssignmentService implements IassignmentService {
    public IAssignmentrepo iAssignmentrepo;
    @Override
    public List<Assignment> getAll() {
        return iAssignmentrepo.findAll();
    }

    @Override
    public Assignment ajassignment(Assignment a) {
        return iAssignmentrepo.save(a);
    }

    @Override
    public List<Assignment> retrieveAll() {
        return iAssignmentrepo.findAll();
    }

    @Override
    public void remove(int idassign) {
        Assignment assign = iAssignmentrepo.findById(idassign).orElse(null);
        iAssignmentrepo.delete(assign);
    }

    @Override
    public Assignment update(int id,Assignment assign) {
        assign.setIdassignment(id);
        return iAssignmentrepo.save(assign);
    }

    @Override
    public List<Assignment> getAssignmentEtudiant(int idassignment) {
        return null;
    }
    @Override
    public Optional<Assignment> getAssign(int idassign) {
        return iAssignmentrepo.findById(idassign);
    }

}

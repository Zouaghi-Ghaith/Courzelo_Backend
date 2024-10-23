package com.example.corzello.Service;


import com.example.corzello.Entity.Etudiant;
import com.example.corzello.Repository.Ietudiantrepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@AllArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Slf4j
@Service
public class etudiantservice implements IetudiantService{
    public Ietudiantrepository ietudiantrepo;

    @Override
    public List<Etudiant> getAll() {
        return ietudiantrepo.findAll();
    }

    @Override
    public Etudiant ajetudiant(Etudiant etud) {
        return ietudiantrepo.save(etud);
    }
}

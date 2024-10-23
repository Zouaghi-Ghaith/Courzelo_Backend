package com.example.corzello.Service;


import com.example.corzello.Entity.classManagement;
import com.example.corzello.Entity.Etudiant;
import com.example.corzello.Entity.Prof;
import com.example.corzello.Repository.Iclassrepo;
import com.example.corzello.Repository.Ietudiantrepository;
import com.example.corzello.Repository.Iprofrepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Slf4j
@Service
public class classservicesimpl implements IclassService{

    @Autowired
    Iclassrepo iclassrepo;


    @Autowired
    Ietudiantrepository ietudiantrepository;

    @Autowired
    Iprofrepo iprofrepo;


@Override
public classManagement ajouterclass(classManagement cm ) {
return iclassrepo.save(cm);
}



    @Override
    public List<classManagement> retrieveAll() {
        return iclassrepo.findAll();
    }

    @Override
    public void removeclass(int idclass) {
        iclassrepo.deleteById(idclass);
    }

    @Override
    public classManagement getclass(int idclass) {
        return iclassrepo.findById(idclass).orElse(null);
    }

    @Override
    public classManagement updateclassManagement(classManagement cm) {
        return iclassrepo.save(cm);
    }
    @Transactional
    @Override
    public classManagement affecteretudiantAClass(int idetudiant, String nomClass) {
        // Retrieve the class
        classManagement classManagement = iclassrepo.findByNomClass(nomClass);

        // Retrieve the student by id
        Etudiant etudiant = ietudiantrepository.findById(idetudiant).orElse(null);

        if (classManagement != null && etudiant != null) {
            // Set the class for the student
            etudiant.setClassM(classManagement);

            // Add the student to the list of students in class
            classManagement.getEtudiants().add(etudiant);

            // Save the updated entities
            ietudiantrepository.save(etudiant);
            iclassrepo.save(classManagement);
        }

        return classManagement;




    }
    @Transactional
    @Override
    public classManagement affecterprofAClass(int idprof, String nomClass) {
        // Retrieve the class
        classManagement classManagement = iclassrepo.findByNomClass(nomClass);

        // Retrieve the professor by id
        Prof professor = iprofrepo.findById(idprof).orElse(null);

        if (classManagement != null && professor != null) {
            // Set the class for the professor
            professor.setClassMa(classManagement);

            // Add the professor to the list of professors in class
            classManagement.getProfs().add(professor);

            // Save the updated entities
            iprofrepo.save(professor);
            iclassrepo.save(classManagement);
        }

        return classManagement;
    }

    @Override
    public List<Etudiant> getEtudiant(int idClass) {
        List<Etudiant> list = ietudiantrepository.findAll();
        return list.stream().filter(p->p.getClassM().getIdclass()==idClass).toList();
    }


}

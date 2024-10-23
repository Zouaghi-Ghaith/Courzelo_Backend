package com.example.corzello.Service;



import com.example.corzello.Entity.classManagement;
import com.example.corzello.Entity.Etudiant;

import java.util.List;

public interface IclassService {




    public classManagement ajouterclass(classManagement cm );
    public List<classManagement> retrieveAll();
    public void removeclass(int idclass);


    classManagement getclass(int idclass );
    public classManagement updateclassManagement(classManagement cm);
    public classManagement affecteretudiantAClass(int idStudent, String className);
    public classManagement affecterprofAClass(int idprof, String className);

    List<Etudiant> getEtudiant(int idClass);
}

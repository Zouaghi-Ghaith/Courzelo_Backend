package com.example.corzello.Controller;


import com.example.corzello.Entity.classManagement;
import com.example.corzello.Entity.Etudiant;
import com.example.corzello.Service.IclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

@RestController
@RequestMapping("/class")
public class classC {

    @Autowired
    IclassService iclassService ;

    @PostMapping("/addClass")
    @ResponseBody
    public classManagement ajouterclass (@RequestBody classManagement cm){
        return iclassService.ajouterclass(cm);
    }
    @GetMapping("/all")
    public List<classManagement> retrieveAll() {
        return iclassService.retrieveAll();
    }

    @PutMapping("/updatecm/{idclass}")
    public classManagement updateclassManagement(@PathVariable int idclass, @RequestBody classManagement updatecm) {
        classManagement existingClass = iclassService.getclass(idclass);
        if (existingClass != null) {

            existingClass.setNomClass(updatecm.getNomClass());
            existingClass.setFiliere(updatecm.getFiliere());

            return iclassService.updateclassManagement(existingClass);
        } else {
            return null;
        }
    }

    @GetMapping("/{idclass}")
    public classManagement retrieveClass(@PathVariable int idclass) {
        return iclassService.getclass(idclass);
    }

    @DeleteMapping("/{idclass}")
    public void removeclass(@PathVariable int idclass) {
        iclassService.removeclass(idclass);
    }


    @PutMapping("/assign/{idetudiant}/{nomClass}")
    public classManagement affecteretudiantAClass(@PathVariable int idetudiant, @PathVariable String nomClass){
        return iclassService.affecteretudiantAClass(idetudiant,nomClass);
    }
    @PutMapping("/assignProfessor/{idprof}/{nomClass}")
    public classManagement affecterprofAClass(@PathVariable int idprof, @PathVariable String nomClass){
        return iclassService.affecterprofAClass(idprof, nomClass);
    }
    @GetMapping("/{idClass}/etudiant")
    public List<Etudiant> getidclass(@PathVariable int idClass) {
        return iclassService.getEtudiant(idClass);
    }
}

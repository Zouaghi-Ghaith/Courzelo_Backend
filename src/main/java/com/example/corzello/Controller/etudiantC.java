package com.example.corzello.Controller;


import com.example.corzello.Entity.Etudiant;
import com.example.corzello.Service.IetudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

@RestController
@RequestMapping("/etudiant")
@AllArgsConstructor
public class etudiantC {

    IetudiantService ietudiantService ;

    @PostMapping("/add")
    @ResponseBody
    public Etudiant ajouteretudiant (@RequestBody Etudiant e){
        return ietudiantService.ajetudiant(e);
    }
    @GetMapping("/all")
    public List<Etudiant> retrieveAll() {
        return ietudiantService.getAll();
    }

}

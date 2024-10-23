package com.example.corzello.Controller;


import com.example.corzello.Entity.Assignment;
import com.example.corzello.Service.IassignmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

@RestController
@RequestMapping("/assign")
@AllArgsConstructor
public class AssignmentC {

    IassignmentService iassignmentService ;

    @PostMapping("/add")
    @ResponseBody
    public Assignment ajouterassign (@RequestBody Assignment assign){

        return iassignmentService.ajassignment(assign);
    }
    @GetMapping("/all")
    public List<Assignment> retrieveAll() {
        return iassignmentService.retrieveAll();
    }

    @PutMapping("/update/{idassign}")
    public Assignment updateassignManagement(@PathVariable int idassign, @RequestBody Assignment assign) {
        return iassignmentService.update(idassign,assign);
    }

    @DeleteMapping("/{idassign}")
    public void removeclass(@PathVariable int idassign) {
        iassignmentService.remove(idassign);
    }

    @GetMapping("/class/{idassign}")
    public List<Assignment> getidclass(@PathVariable int idetud) {
        return iassignmentService.getAssignmentEtudiant(idetud);
    }
    @GetMapping("/{idassign}")
    public Optional<Assignment> getAssign(@PathVariable int idassign) {
        return iassignmentService.getAssign(idassign);
    }





}

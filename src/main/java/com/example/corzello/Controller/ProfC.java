package com.example.corzello.Controller;


import com.example.corzello.Entity.Prof;
import com.example.corzello.Service.IprofService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")

@RestController
@RequestMapping("/prof")
@AllArgsConstructor
public class ProfC {

    IprofService iprofService ;

    @PostMapping("/add")
    @ResponseBody
    public Prof ajouterprof (@RequestBody Prof p){
        return iprofService.ajprof(p);
    }
    @GetMapping("/all")
    public List<Prof> retrieveAll() {
        return iprofService.retrieveAll();
    }

    @PutMapping("/updatecm/{idprof}")
    public Prof updateclassManagement(@PathVariable int idprof, @RequestBody Prof profx) {
        return iprofService.update(idprof,profx);
    }

    @DeleteMapping("/{idprof}")
    public void removeclass(@PathVariable int idprof) {
        iprofService.remove(idprof);
    }

    @GetMapping("/class/{idClass}")
    public List<Prof> getidclass(@PathVariable int idClass) {
        return iprofService.getProfClass(idClass);
    }





}

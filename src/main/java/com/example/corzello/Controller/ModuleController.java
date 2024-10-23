package com.example.corzello.Controller;

import com.example.corzello.Entity.ModuleEntity;

import com.example.corzello.Service.ModuleServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("Module")
@RequiredArgsConstructor

public class ModuleController implements Serializable {

    @Autowired
    private ModuleServcie moduleService;

   @GetMapping("getAll")
   @CrossOrigin(origins = "http://localhost:4200")
    public List<ModuleEntity> getAllModules() {
        return moduleService.getAllModule();
   }






   //  public ResponseEntity<ModuleEntity> createModule(@RequestBody ModuleEntity module) {
     //   ModuleEntity savedModule = moduleService.saveOrUpdateModule(module);
       // return new ResponseEntity<>(savedModule, HttpStatus.CREATED);
    //}




    @PostMapping("/add/{idProg}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ModuleEntity> addModule(@PathVariable Long idProg, @RequestBody ModuleEntity module) {
       ModuleEntity savedModule = moduleService.AjouterModule(idProg, module);
       return new ResponseEntity<>(savedModule, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/progModule/{progEducId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<ModuleEntity> getModulesForProgEduc(@PathVariable Long progEducId) {
        return moduleService.getModulesForProgEduc(progEducId);
    }


}

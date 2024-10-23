package com.example.corzello.Service;

import com.example.corzello.Entity.ModuleEntity;
import com.example.corzello.Entity.Prog_educatif;
import com.example.corzello.Repository.ModuleRepository;
import com.example.corzello.Repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ModuleServiceImpl implements ModuleServcie{

    public ModuleServiceImpl(ModuleRepository moduleRepository, ProgramRepository programRepository) {
        this.moduleRepository = moduleRepository;
        this.programRepository = programRepository;
    }


    @Autowired
    private ModuleRepository moduleRepository;
    private ProgramRepository programRepository;



    public Optional<ModuleEntity> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    @Override
    public ModuleEntity AjouterModule(Long idProg, ModuleEntity module) {
        Prog_educatif Program=this.programRepository.findById(idProg).orElse(null);
        if(Program != null){
            module.setProgEducatif(Program);
        }
        return this.moduleRepository.save(module);
    }



    public void deleteModule(Long id) {
        moduleRepository.deleteById(id);
    }

    @Override
    public List<ModuleEntity> getAllModule() {
        return (List<ModuleEntity>) moduleRepository.findAll();
    }

    public List<ModuleEntity> getModulesForProgEduc(Long progEducId) {
        // Récupérer le programme éducatif depuis la base de données
        Prog_educatif progEduc = programRepository.findById(progEducId)
                .orElseThrow(() -> new RuntimeException("Programme éducatif non trouvé"));

        // Convertir le Set de modules en List de modules et le retourner
        return progEduc.getModules().stream().collect(Collectors.toList());
    }


}



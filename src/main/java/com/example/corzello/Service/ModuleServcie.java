package com.example.corzello.Service;

import com.example.corzello.Entity.ModuleEntity;

import java.util.List;
import java.util.Optional;

public interface ModuleServcie {


    public Optional<ModuleEntity> getModuleById(Long id);
    public ModuleEntity AjouterModule(Long idProg, ModuleEntity module);

    public void deleteModule(Long id);
    public List<ModuleEntity> getAllModule();
    public List<ModuleEntity> getModulesForProgEduc(Long progEducId);

}

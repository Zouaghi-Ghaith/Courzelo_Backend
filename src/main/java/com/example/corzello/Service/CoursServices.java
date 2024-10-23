package com.example.corzello.Service;


import com.example.corzello.Entity.Cours;

import com.example.corzello.Repository.CourRepo;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CoursServices implements ICourServices {

    @Autowired
   private CourRepo courRepo;

    @Override

    public Cours addCours(Cours cours) {

        System.out.println(cours.getAddedAt());
    return courRepo.save(cours);
    }

    @Override
    public Cours updateCours(Cours cours,long idCours) {

        return courRepo.save(cours);
    }

    @Override
    public void deleteCour(long idCours) {
        courRepo.deleteById(idCours);
    }

    @Override
    public List<Cours> getAllCours() {
        return  courRepo.findAll();
    }


}

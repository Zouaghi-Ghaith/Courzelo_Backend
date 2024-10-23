package com.example.corzello.Controller;

import com.example.corzello.Entity.Question;
import com.example.corzello.Entity.reponses_etudiant;
import com.example.corzello.Service.QuestionService;
import com.example.corzello.Service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("response")
@RequiredArgsConstructor
public class responseEtdntController {
    @Autowired
    private ResponseService responseService;


    @PostMapping("/add/{idQuestion}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<reponses_etudiant> addReponseEtdnt(@PathVariable Long idQuestion, @RequestBody reponses_etudiant reponseEtudiant) {
        reponses_etudiant savedResponse = responseService.AjouterResponse(idQuestion, reponseEtudiant);
        return new ResponseEntity<>(savedResponse, HttpStatus.CREATED);
    }
}

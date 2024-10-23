package com.example.corzello.Service;

import com.example.corzello.Entity.Question;
import com.example.corzello.Entity.Quiz;
import com.example.corzello.Entity.reponses_etudiant;

import java.util.List;

public interface ResponseService {
    public reponses_etudiant AjouterResponse(Long idQuestion, reponses_etudiant reponse);
}

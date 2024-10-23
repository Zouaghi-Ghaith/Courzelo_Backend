package com.example.corzello.Service;

import com.example.corzello.Entity.ModuleEntity;
import com.example.corzello.Entity.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    public Optional<Quiz> getQuizById(Long idQuiz);
    public Quiz AjouterQuiz(Long idModule, Quiz quiz, Long userId);

    public void deleteQuiz(Long idQuiz);
    public List<Quiz> getAllQuiz();
    public List<Quiz> getAllQuiz(Long idModule);
    public List<Quiz> getQuizByModule(Long idModule, Long userId);



}

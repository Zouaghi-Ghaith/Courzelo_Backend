package com.example.corzello.Service;

import com.example.corzello.Entity.Question;
import com.example.corzello.Entity.Quiz;
import com.example.corzello.Entity.reponses_etudiant;
import com.example.corzello.Repository.QuestionRepository;
import com.example.corzello.Repository.QuizRepository;
import com.example.corzello.Repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {
    private final ResponseRepository responseRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository, QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.responseRepository = responseRepository;
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public reponses_etudiant AjouterResponse(Long idQuestion, reponses_etudiant reponse) {
        Question question = this.questionRepository.findById(idQuestion).orElse(null);
        if (question != null) {
            if (question.getCorrect().equalsIgnoreCase(reponse.getResponseEtudiant())) {
                reponse.setResult(true);
            } else {
                reponse.setResult(false);
            }
            reponse.setQuestion(question);
        }

        reponses_etudiant savedResponse = this.responseRepository.save(reponse);

        // Mettre à jour le score du quiz après avoir ajouté la réponse
        updateQuizScore(question.getQuiz()); // Ne pas passer la note de la question ici

        return savedResponse;
    }

    private void updateQuizScore(Quiz quiz) {
        if (quiz != null) {
            float currentScore = 0;
            // Calculer le score total en parcourant les réponses associées aux questions du quiz
            for (Question question : quiz.getQuestions()) {
                reponses_etudiant response = this.responseRepository.findByQuestion(question);
                if (response != null && response.isResult()) {
                    // Ajouter la note de la question uniquement si la réponse de l'étudiant est correcte
                    currentScore += question.getNote();
                }
            }
            // Mettre à jour le score du quiz
            quiz.setScore(currentScore);
            quiz.setPassed(true);
            this.quizRepository.save(quiz);
        }
    }
}

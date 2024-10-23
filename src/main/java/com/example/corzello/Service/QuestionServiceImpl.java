package com.example.corzello.Service;

import com.example.corzello.Entity.ModuleEntity;
import com.example.corzello.Entity.Question;
import com.example.corzello.Entity.Quiz;
import com.example.corzello.Repository.ModuleRepository;
import com.example.corzello.Repository.QuestionRepository;
import com.example.corzello.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService{
    public QuestionServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }


    @Autowired
    private QuestionRepository questionRepository;
    private QuizRepository quizRepository;

    @Override
    public Optional<Question> getQuestionById(Long idQuestion) {
        return questionRepository.findById(idQuestion);
    }

    @Override
    public Question AjouterQuestion(Long idQuiz, Question question) {
        Quiz quiz=this.quizRepository.findById(idQuiz).orElse(null);
        if(quiz != null){
            question.setQuiz(quiz);
        }
        return this.questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long idQuestion) {
        questionRepository.deleteById(idQuestion);
    }

    @Override
    public List<Question> getAllQuestion() {
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public List<Question> getQuestionByQuiz(Long idQuiz) {
        // Récupérer le programme éducatif depuis la base de données
        Quiz quiz = quizRepository.findById(idQuiz)
                .orElseThrow(() -> new RuntimeException("Quiz  non trouvé"));

        return quiz.getQuestions().stream().collect(Collectors.toList());
    }
}


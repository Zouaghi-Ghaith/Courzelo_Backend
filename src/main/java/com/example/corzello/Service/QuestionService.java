package com.example.corzello.Service;

import com.example.corzello.Entity.Question;
import com.example.corzello.Entity.Quiz;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    public Optional<Question> getQuestionById(Long idQuestion);

    public Question AjouterQuestion(Long idQuiz, Question question);

    //@Override
    //public Question AjouterQuestion(Long idQuiz, Question question) {
    //  Quiz quiz=this.quizRepository.findById(idQuiz).orElse(null);
    //  if(quiz != null){
    //    question.setQuiz(quiz);
    // }
    // return this.questionRepository.save(question);
    // }
    public void deleteQuestion(Long idQuestion);

    public List<Question> getAllQuestion();

    public List<Question> getQuestionByQuiz(Long idQuiz);
}
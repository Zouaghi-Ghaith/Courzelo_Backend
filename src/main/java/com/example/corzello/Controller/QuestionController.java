package com.example.corzello.Controller;

import com.example.corzello.Entity.Question;
import com.example.corzello.Entity.Quiz;
import com.example.corzello.Service.QuestionService;
import com.example.corzello.Service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Question")
@RequiredArgsConstructor
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("getAll")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestion();
    }


    @PostMapping("/add/{idQuiz}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Question> addQuestion(@PathVariable Long idQuiz, @RequestBody Question question) {
        Question savedQuestion = questionService.AjouterQuestion(idQuiz, question);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idQuestion}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long idQuestion) {
        questionService.deleteQuestion(idQuestion);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/QuestionByQuiz/{idQuiz}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Question> getQuestionByQuiz(@PathVariable Long idQuiz) {
        return questionService.getQuestionByQuiz(idQuiz);
    }


}


package com.example.corzello.Controller;

import com.example.corzello.Entity.Quiz;
import com.example.corzello.Service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quiz")
@RequiredArgsConstructor
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping("getAll")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Quiz> getAllQuiz() {
        return quizService.getAllQuiz();
    }


    @PostMapping("/add/{idModule}/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Quiz> addQuiz(@PathVariable Long idModule, @RequestBody Quiz quiz , @PathVariable Long userId) {
        Quiz savedQuiz = quizService.AjouterQuiz(idModule, quiz, userId);
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idQuiz}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long idQuiz) {
        quizService.deleteQuiz(idQuiz);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ModuleQuiz/{idModule}/{userId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Quiz> getModulesForProgEduc(@PathVariable Long idModule, @PathVariable Long userId) {
        return quizService.getQuizByModule(idModule, userId);
    }
    @GetMapping("/ModuleQuiz/{idModule}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Quiz> getModulesForProgEduc(@PathVariable Long idModule) {
        return quizService.getAllQuiz(idModule);
    }



}

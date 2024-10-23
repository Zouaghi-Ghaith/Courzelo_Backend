package com.example.corzello.Service;

import com.example.corzello.Entity.ModuleEntity;
import com.example.corzello.Entity.Prog_educatif;
import com.example.corzello.Entity.Quiz;
import com.example.corzello.Entity.UserEntity;
import com.example.corzello.Repository.ModuleRepository;
import com.example.corzello.Repository.ProgramRepository;
import com.example.corzello.Repository.QuizRepository;
import com.example.corzello.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {

    public QuizServiceImpl(ModuleRepository moduleRepository,UserRepo userRepository, QuizRepository quizRepository) {
        this.moduleRepository = moduleRepository;
        this.quizRepository = quizRepository;
        this.userRepository=userRepository;
    }


    @Autowired
    private ModuleRepository moduleRepository;
    private QuizRepository quizRepository;
    @Autowired
    private UserRepo userRepository;

    @Override
    public Optional<Quiz> getQuizById(Long idQuiz) {
        return quizRepository.findById(idQuiz);
    }

    @Override
    public Quiz AjouterQuiz(Long idModule, Quiz quiz, Long userId) {
        ModuleEntity Module =this.moduleRepository.findById(idModule).orElse(null);
        if(Module != null){
            quiz.setModuleEntity(Module);
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + userId));
            quiz.setUser(user);
        }
        return this.quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long idQuiz) {
        quizRepository.deleteById(idQuiz);
    }

    @Override
    public List<Quiz> getAllQuiz() {
        return (List<Quiz>) quizRepository.findAll();
    }
    @Override
    public List<Quiz> getQuizByModule(Long idModule, Long userId) {
        // Trouver le module par son id
        ModuleEntity module = moduleRepository.findById(idModule)
                .orElseThrow(() -> new RuntimeException("Module non trouvé"));

        // Récupérer tous les quizzes associés au module
        Set<Quiz> quizzes = module.getQuizzes();

        // Filtrer les quizzes pour ceux qui sont liés à l'utilisateur ou qui sont passés
        List<Quiz> userQuizzes = quizzes.stream()
                .filter(quiz -> {
                    UserEntity user = quiz.getUser();
                    Long quizUserId = user != null ? user.getId() : null;
                    return userId != null && userId.equals(quizUserId) || (quiz.isPassed() && quiz.getScore() >= 10);
                })
                .collect(Collectors.toList());

        if (userQuizzes.isEmpty()) {
            return Collections.emptyList();
        }

        List<Quiz> passedQuizzes = userQuizzes.stream()
                .filter(Quiz::isPassed)
                .collect(Collectors.toList());

        List<Quiz> showQuiz = new ArrayList<>();

        // Ajouter les quizzes passés à la liste showQuiz
        showQuiz.addAll(passedQuizzes);

        // Récupérer le niveau maximal des quizzes passés
        int maxPassedLevel = passedQuizzes.stream()
                .mapToInt(Quiz::getNiveau)
                .max()
                .orElse(0);

        // Récupérer les quizzes non passés du niveau suivant du niveau maximal des quizzes passés
        List<Quiz> nextLevelQuizzes = userQuizzes.stream()
                .filter(quiz -> quiz.getNiveau() == maxPassedLevel + 1 && !quiz.isPassed())
                .collect(Collectors.toList());

        if (!passedQuizzes.isEmpty()) {
            if (passedQuizzes.get(passedQuizzes.size() - 1).getScore() >= 10 && !nextLevelQuizzes.isEmpty()) {
                showQuiz.add(nextLevelQuizzes.get(0));
            } else {
                // Récupérer les quizzes non passés du même niveau que le niveau maximal des quizzes passés
                List<Quiz> sameLevelQuizzes = userQuizzes.stream()
                        .filter(quiz -> quiz.getNiveau() == maxPassedLevel && !quiz.isPassed())
                        .collect(Collectors.toList());

                if (!sameLevelQuizzes.isEmpty()) {

                    showQuiz.add(sameLevelQuizzes.get(0));
                }
            }
        }

        // Si aucun quiz n'a été trouvé selon les conditions, retournez une liste vide
        if (showQuiz.isEmpty()) {
            return Collections.emptyList();
        }

        return showQuiz;
    }


    @Override
    public List<Quiz> getAllQuiz(Long idModule) {
        // Récupérer le programme éducatif depuis la base de données
        ModuleEntity module = moduleRepository.findById(idModule)
                .orElseThrow(() -> new RuntimeException("Module  non trouvé"));

        return module.getQuizzes().stream().collect(Collectors.toList());
    }


}

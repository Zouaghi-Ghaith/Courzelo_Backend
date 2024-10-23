package com.example.corzello.Controller;

import com.example.corzello.Entity.Commentaire;

import com.example.corzello.Service.CommentaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/comments")

public class CommentaireController {

    private final CommentaireService commentaireService;

    @Autowired
    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @PostMapping("/add/{idPublication}")
    public ResponseEntity<Commentaire> addComment(@PathVariable Long idPublication, @RequestBody Commentaire commentaire) {
        Commentaire savedComment = commentaireService.createComment(idPublication, commentaire);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping("/comments/{idPublication}")
    public ResponseEntity<List<Commentaire>> getCommentsByPublicationId(@PathVariable Long idPublication) {
        List<Commentaire> comments = commentaireService.getCommentsByPublicationId(idPublication);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    @PutMapping("/update/{idCommentaire}")
    public ResponseEntity<Commentaire> updateComment(@PathVariable Long idCommentaire, @RequestBody Commentaire commentaire) {
        Commentaire updatedComment = commentaireService.updateComment(idCommentaire, commentaire);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idCommentaire}")
    public ResponseEntity<?> deleteComment(@PathVariable Long idCommentaire) {
        commentaireService.deleteComment(idCommentaire);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/user/{userId}/count")
    public ResponseEntity<Integer> countCommentsByUserId(@PathVariable Long userId) {
        int commentCount = commentaireService.countCommentsByUserId(userId);
        return new ResponseEntity<>(commentCount, HttpStatus.OK);
    }
}

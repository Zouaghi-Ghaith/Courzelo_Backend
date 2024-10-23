package com.example.corzello.Controller;

import com.example.corzello.Service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class VoteController {
    private final VoteService voteService;

    @PutMapping("/upvote/{publicationId}")
    public ResponseEntity<?> upvotePublication(@PathVariable Long publicationId) {
        if (voteService.upvotePublication(publicationId)) {
            return ResponseEntity.ok().body("{\"message\": \"Publication upvoted successfully.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Publication not found.\"}");
        }
    }

    @PutMapping("/downvote/{publicationId}")
    public ResponseEntity<?> downvotePublication(@PathVariable Long publicationId) {
        if (voteService.downvotePublication(publicationId)) {
            return ResponseEntity.ok().body("{\"message\": \"Publication downvoted successfully.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Publication not found.\"}");
        }
    }
}

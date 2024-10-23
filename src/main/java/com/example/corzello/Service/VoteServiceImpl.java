package com.example.corzello.Service;

import com.example.corzello.Entity.Publication;
import com.example.corzello.Entity.Vote;
import com.example.corzello.Repository.PublicationRepository;
import com.example.corzello.Repository.VoteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Slf4j
@Service
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final PublicationRepository publicationRepository;

    @Override
    public boolean upvotePublication(Long publicationId) {
        Optional<Publication> publicationOptional = publicationRepository.findById(publicationId);
        if (publicationOptional.isPresent()) {
            Publication publication = publicationOptional.get();
            // Update the upvote count
            publication.setUpvoteCount(publication.getUpvoteCount() + 1);
            // Save the publication
            publicationRepository.save(publication);
            return true;
        }
        return false;
    }

    @Override
    public boolean downvotePublication(Long publicationId) {
        Optional<Publication> publicationOptional = publicationRepository.findById(publicationId);
        if (publicationOptional.isPresent()) {
            Publication publication = publicationOptional.get();
            // Update the downvote count
            publication.setDownvoteCount(publication.getDownvoteCount() + 1);
            // Save the publication
            publicationRepository.save(publication);
            return true;
        }
        return false;
    }
}

package com.example.corzello.Service;

import com.example.corzello.Entity.Commentaire;
import com.example.corzello.Entity.Publication;
import com.example.corzello.Repository.CommentaireRepository;
import com.example.corzello.Repository.PublicationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentaireServiceImpl implements CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final PublicationRepository publicationRepository;

    @Autowired
    public CommentaireServiceImpl(CommentaireRepository commentaireRepository, PublicationRepository publicationRepository) {
        this.commentaireRepository = commentaireRepository;
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Commentaire createComment(Long idPublication, Commentaire commentaire) {
        Publication publication = publicationRepository.findById(idPublication)
                .orElseThrow(() -> new EntityNotFoundException("Publication not found with ID: " + idPublication));
        commentaire.setPublication(publication);
        commentaire.setCreatedAt(new Date());
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> getCommentsByPublicationId(Long idPublication) {
        return commentaireRepository.findByPublicationIdPublication(idPublication);
    }

    @Override
    public Commentaire updateComment(Long idCommentaire, Commentaire updatedComment) {
        Optional<Commentaire> existingCommentOptional = commentaireRepository.findById(idCommentaire);
        if (existingCommentOptional.isPresent()) {
            Commentaire existingComment = existingCommentOptional.get();
            existingComment.setContent(updatedComment.getContent());
            return commentaireRepository.save(existingComment);
        } else {
            throw new EntityNotFoundException("Comment not found with ID: " + idCommentaire);
        }
    }

    @Override
    public void deleteComment(Long idCommentaire) {
        commentaireRepository.deleteById(idCommentaire);
    }
    @Override
    public int countCommentsByPublicationId(Long idPublication) {
        return commentaireRepository.countByPublicationIdPublication(idPublication);
    }

    @Override
    public int countCommentsByUserId(Long userId) {
        return commentaireRepository.countByUserId(userId);
    }

}

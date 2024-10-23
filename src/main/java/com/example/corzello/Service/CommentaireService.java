package com.example.corzello.Service;

import com.example.corzello.Entity.Commentaire;

import java.util.List;

public interface CommentaireService {

    public int countCommentsByPublicationId(Long idPublication);
    public int countCommentsByUserId(Long userId);
    Commentaire createComment(Long idPublication, Commentaire commentaire);

    List<Commentaire> getCommentsByPublicationId(Long idPublication);

    Commentaire updateComment(Long idCommentaire, Commentaire commentaire);

    void deleteComment(Long idCommentaire);
}

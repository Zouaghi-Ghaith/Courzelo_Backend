package com.example.corzello.Service;

import com.example.corzello.Entity.Publication;

import java.util.List;

public interface PublicationService {

    public Publication ajouterPublication(Publication publication, Long userId);
    public Publication updatePublication(Publication publication);
    public Publication getPublicationById(long idPublication);
    public List<Publication> getAllPublication();
    public void deletePublication(long idPublication);
}

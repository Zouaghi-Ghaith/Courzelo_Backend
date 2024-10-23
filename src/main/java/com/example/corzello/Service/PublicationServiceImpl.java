package com.example.corzello.Service;

import com.example.corzello.Entity.Publication;
import com.example.corzello.Entity.Publication;
import com.example.corzello.Entity.UserEntity;
import com.example.corzello.Repository.PublicationRepository;
import com.example.corzello.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl  implements  PublicationService{

    @Autowired
    private UserRepo userRepository ;
    private PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository){
        this.publicationRepository=publicationRepository;
    }


    @Override
    public Publication ajouterPublication(Publication publication, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + userId));
        publication.setUser(user);
        return publicationRepository.save(publication);
    }


    @Override
    @Transactional
    public Publication updatePublication(Publication publication) {
        // Retrieve the existing publication from the database
        Optional<Publication> existingPublicationOptional = publicationRepository.findById(publication.getIdPublication());
        if (existingPublicationOptional.isPresent()) {
            // Update the existing publication with the new values
            Publication existingPublication = existingPublicationOptional.get();
            existingPublication.setTitle(publication.getTitle());
            existingPublication.setDescription(publication.getDescription());
            existingPublication.setBody(publication.getBody());
            existingPublication.setTags(publication.getTags());
            // Save the updated publication
            return publicationRepository.save(existingPublication);
        } else {
            // Handle the case where the publication with the given ID doesn't exist
            throw new EntityNotFoundException("Publication not found with ID: " + publication.getIdPublication());
        }
    }

    @Override
    public Publication getPublicationById(long idPublication) {
        return  publicationRepository.findById(idPublication).orElse(null);
    }

    @Override
    public List<Publication> getAllPublication() {
        return (List<Publication>) publicationRepository.findAll()  ;
    }

    @Override
    public void deletePublication(long idPublication) {
        publicationRepository.deleteById(idPublication);

    }
}

package com.example.corzello.Controller;

import com.example.corzello.Entity.Community;
import com.example.corzello.Entity.Publication;
import com.example.corzello.Service.CommunityService;
import com.example.corzello.Service.PublicationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/publications")
@RequiredArgsConstructor
public class PublicationController {
    @Autowired
    private CommunityService communityService ;
    @Autowired
    private PublicationServiceImpl publicationService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("getAll")
    public List<Publication> getAllPublications() {
        return publicationService.getAllPublication();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{idPublication}")
    public Publication getPublicationById(@PathVariable Long idPublication) {
        return publicationService.getPublicationById(idPublication);
    }

    @CrossOrigin(origins = "http://localhost:4200")

    @PostMapping("/add/{userId}")
    public Publication ajouterPublication(@RequestBody Publication publication, @PathVariable Long userId) {
        return publicationService.ajouterPublication(publication,userId);
    }
    @CrossOrigin(origins = "http://localhost:4200")

    @DeleteMapping("/delete/{idPublication}")
    public void deletePublication(@PathVariable Long idPublication) {
        publicationService.deletePublication(idPublication);
    }
    @CrossOrigin(origins = "http://localhost:4200")

    @PutMapping("/update/{idPublication}")
    public Publication updatePublication(@RequestBody Publication publication) {
        return publicationService.updatePublication(publication);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/markTop")
    public void markTopPublications() {
        List<Publication> publications = publicationService.getAllPublication();
        List<Community> communities = communityService.getAllCommunities();

        for (Publication publication : publications) {
            boolean isTopPublication = false; // Initialize as false

            // Check if publication tags contain any community name
            for (Community community : communities) {
                if (publication.getTags().contains(community.getName())) {
                    isTopPublication = true; // Set as top publication
                    break; // No need to continue searching once a match is found
                }
            }

            // Update publication's topPublication flag
            publication.setTopPublication(isTopPublication);
            publicationService.updatePublication(publication); // Update in database
        }
    }

}



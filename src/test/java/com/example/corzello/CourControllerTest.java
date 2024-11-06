package com.example.corzello;

import com.example.corzello.Controller.CourController;
import com.example.corzello.Entity.Cours;
import com.example.corzello.Repository.CourRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CourControllerTest {

    @InjectMocks
    private CourController courController;

    @Mock
    private CourRepo courRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCourByIdFound() {
        // Arrange : Préparez l'ID et le cours fictif
        Long idCours = 1L;
        Cours cours = new Cours();
        cours.setIdCours(idCours);
        cours.setNomCour("Cours Test");

        // Simulez que le repository retourne un cours pour cet ID
        when(courRepo.findById(idCours)).thenReturn(Optional.of(cours));

        // Act : Appelez la méthode du contrôleur
        ResponseEntity<Cours> response = courController.getCourById(idCours);

        // Assert : Vérifiez le statut et le contenu de la réponse
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cours, response.getBody());
    }

    @Test
    void testGetCourByIdNotFound() {
        // Arrange : Préparez l'ID pour lequel aucun cours n'est trouvé
        Long idCours = 2L;

        // Simulez que le repository retourne un Optional.empty() pour cet ID
        when(courRepo.findById(idCours)).thenReturn(Optional.empty());

        // Act & Assert : Vérifiez que l'exception est levée
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            courController.getCourById(idCours);
        });

        // Vérifiez le message d'erreur de l'exception
        assertEquals("Cours with ID " + idCours + " not found.", exception.getMessage());
    }
}

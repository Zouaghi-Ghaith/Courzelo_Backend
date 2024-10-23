package com.example.corzello.Controller;
import com.example.corzello.Entity.proposed_job_offer;
import com.example.corzello.Service.PythonScriptRunner;
import com.example.corzello.Service.ScrapingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/scrape")
@CrossOrigin(origins = "http://localhost:4200")
public class ScrapingController {
    private final RestTemplate restTemplate;

    private final ScrapingService scrapingService;

    public ScrapingController(RestTemplate restTemplate, ScrapingService scrapingService) {
        this.restTemplate = restTemplate;
        this.scrapingService = scrapingService;
    }

    @PostMapping("/api/scrape/{searchTerm}")
    public ResponseEntity<Object> callPythonAI(@PathVariable String searchTerm) {
        PythonScriptRunner psr = new PythonScriptRunner();
        // Pass the searchTerm to the Python script or perform any necessary operations

        boolean success = psr.runPythonScript(searchTerm); // Example of passing searchTerm to the Python script

        if (success) {
            return ResponseEntity.ok().build(); // Success response
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Error response
        }
    }
    /*@GetMapping("GetAllByUser/")
    public List<proposed_job_offer> getAllProposedJobOffer() {
        return scrapingService.getAllProposedJobOffer( );
    }*/
    @GetMapping("/job-offers/user/{userId}")
    public List<proposed_job_offer> getJobOffersForUser(@PathVariable long userId) {
        return scrapingService.getJobOffersForUser(userId);
    }
}
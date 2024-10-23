package com.example.corzello.Service;

import com.example.corzello.Entity.Recruitement_process_details;
import com.example.corzello.Entity.proposed_job_offer;

import java.util.List;

public interface ScrapingService {
    //List<proposed_job_offer> getAllProposedJobOffer();
    List<proposed_job_offer> getJobOffersForUser(long userId);
}

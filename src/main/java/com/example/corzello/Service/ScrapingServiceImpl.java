package com.example.corzello.Service;

import com.example.corzello.Entity.proposed_job_offer;
import com.example.corzello.Repository.ScrapingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScrapingServiceImpl implements ScrapingService{
    private final ScrapingRepository scrapingRepo;

    public ScrapingServiceImpl(ScrapingRepository scrapingRepo) {
        this.scrapingRepo = scrapingRepo;
    }

    /*@Override
    public List<proposed_job_offer> getAllProposedJobOffer() {
        return scrapingRepo.findAll();
    }*/
    @Override
    public List<proposed_job_offer> getJobOffersForUser(long userId) {
        return scrapingRepo.getJobOffersForUser(userId);}
}

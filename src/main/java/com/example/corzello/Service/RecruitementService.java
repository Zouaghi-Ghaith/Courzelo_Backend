package com.example.corzello.Service;

import com.example.corzello.Entity.Etudiant;
import com.example.corzello.Entity.Recruitement_process_details;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecruitementService {

    Optional<Recruitement_process_details> getRecruitementProcessById(Long id);


    Recruitement_process_details createRecruitementProcess(String etudiantEmail, Recruitement_process_details recruitementProcess) throws MessagingException;


    Recruitement_process_details updateRecruitementProcess(Recruitement_process_details updatedRecruitementProcess);

    void deleteRecruitementProcess(Long id);

    Set<Recruitement_process_details> getAllRecruitementProcessesWithEtudiant();
}

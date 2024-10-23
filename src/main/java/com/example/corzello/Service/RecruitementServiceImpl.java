package com.example.corzello.Service;

import com.example.corzello.Entity.Etudiant;
import com.example.corzello.Entity.Recruitement_process_details;
import com.example.corzello.Entity.UserEntity;
import com.example.corzello.Repository.Ietudiantrepository;
import com.example.corzello.Repository.RecruitementRepo;
import com.example.corzello.Repository.UserRepo;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RecruitementServiceImpl implements RecruitementService {

    private final RecruitementRepo recruitementRepo;
    private final Ietudiantrepository etudiantRepo;
    private final UserRepo userRepo;
    @Autowired
    private  EmailService emailService;

    @Autowired
    public RecruitementServiceImpl(RecruitementRepo recruitementRepo, Ietudiantrepository etudiantRepository, UserRepo userRepo) {

        this.recruitementRepo = recruitementRepo;
        this.etudiantRepo = etudiantRepository;
        this.userRepo = userRepo;
    }

    @Override
    public Set<Recruitement_process_details> getAllRecruitementProcessesWithEtudiant() {
        return recruitementRepo.findAllWithUserEntity();
    }

    @Override
    public Optional<Recruitement_process_details> getRecruitementProcessById(Long id) {
        return recruitementRepo.findById(id);
    }

    @Override
    public Recruitement_process_details createRecruitementProcess(String etudiantEmail, Recruitement_process_details recruitementProcess) throws MessagingException {
        UserEntity e = userRepo.findByEmail(etudiantEmail);
        recruitementProcess.setUser(e);
        emailService.sendemailrecrutement(etudiantEmail);
        return recruitementRepo.save(recruitementProcess);
    }

    @Override
    public Recruitement_process_details updateRecruitementProcess(Recruitement_process_details updatedRecruitementProcess) {

            return recruitementRepo.save(updatedRecruitementProcess);

    }

    @Override
    public void deleteRecruitementProcess(Long id) {
        recruitementRepo.deleteById(id);
    }
}

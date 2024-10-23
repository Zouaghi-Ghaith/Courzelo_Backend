package com.example.corzello.Service;

import com.example.corzello.Controller.AuthenticationRequest;
import com.example.corzello.Controller.AuthenticationResponse;
import com.example.corzello.Controller.RegisterRequest;
import com.example.corzello.Entity.*;
import com.example.corzello.Repository.*;
import com.example.corzello.Security.JwtService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class    UserServiceImpl implements UserService {
    @Autowired
    private UniversiteRepository universiteRepository ;
    @Autowired
    private RecruteurRepository recruteurRepository;
    @Autowired
    private ProfRepository profRepository ;
    @Autowired
    private Ietudiantrepository etudiantRepository ;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailService emailService ;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private JwtService jwtService ;
    @Autowired
    private AuthenticationManager authenticationManager ;
    @Value("${activation-url}")
    private String activationUrl;

    @Override
    public AuthenticationResponse register(RegisterRequest request) throws MessagingException {
       //TODo:RAJA3 ELROLE
        var user =UserEntity.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Role.ROLE_User)
                .build();
        userRepo.save(user) ;
        sendValidationEmail(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().user(user).token(jwtToken).build() ;
    }

    private void sendValidationEmail(UserEntity user) throws MessagingException {

        emailService.sendEmail(
                user.getEmail(),
                user.fullname(),
                "Account activation",
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl
        );
    }



    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepo.findByEmail(request.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        var jwtToken = jwtService.generateToken(user);
        Object associatedEntity = null;
        switch (user.getRoles()) {
            case ROLE_Admin:
            case ROLE_User:
                break;
            case ROLE_Universite:
                associatedEntity = universiteRepository.findById(user.getId());
                break;
            case ROLE_Prof:
                associatedEntity = profRepository.findById(user.getId());
                break;
            case ROLE_Recruteur:
                associatedEntity = recruteurRepository.findById(user.getId());
                break;
            case ROLE_Etudiant:
               // associatedEntity = etudiantRepository.findById(user.getId());
                break;
        }
        return AuthenticationResponse.builder()
                .user(user)
                .associatedEntity((AssociatedEntity) associatedEntity)
                .token(jwtToken)
                .build();
    }
    @Override
    public AuthenticationResponse googleSignIn(String email) {

        var user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }

    @Override
    public Role addRoletoUser(String email, String role) {
        UserEntity user = userRepo.findByEmail(email);
        Role role1 = Role.valueOf("ROLE_"+role);
        user.setRoles(role1);
        switch (role1) {
            case ROLE_Universite:
                Universite universite = new Universite();
                universite.setId_user(user.getId());
                universiteRepository.save(universite);
                break;
          /*  case ROLE_Prof:
                Prof prof = new Prof();
                prof.setId_user(user.getId());
                profRepository.save(prof);
                break; */
            case ROLE_Recruteur:
                Recruteur recruteur = new Recruteur();
                recruteur.setId_user(user.getId());
                recruteurRepository.save(recruteur);
                break;
          /*  case ROLE_Etudiant:
                Etudiant etudiant = new Etudiant();
                etudiant.setId_user(user.getId());
                etudiantRepository.save(etudiant);
                break; */
        }
        return role1 ;
    }

    @Override
    public Optional<UserEntity> getUserById(Long idUser) {
        return userRepo.findById(idUser);
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public String forgotPassword(String email) throws MessagingException {
        UserEntity user = userRepo.findByEmail(email);
        emailService.sendPasswordEmail(email);
        return "please check your email to reser your password";
    }

    @Override
    public String setPassword(String email, String newpassword) {
        UserEntity user = userRepo.findByEmail(email);
        user.setPassword(passwordEncoder.encode(newpassword));
        userRepo.save(user);
        return "new password set successfully";
    }


}

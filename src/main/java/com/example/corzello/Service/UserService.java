package com.example.corzello.Service;

import com.example.corzello.Controller.AuthenticationRequest;
import com.example.corzello.Controller.AuthenticationResponse;
import com.example.corzello.Controller.RegisterRequest;
import com.example.corzello.Entity.Role;
import com.example.corzello.Entity.UserEntity;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    AuthenticationResponse register(RegisterRequest request) throws MessagingException;
    AuthenticationResponse authenticate(AuthenticationRequest request) ;
    public AuthenticationResponse googleSignIn(String email);
    public Role addRoletoUser(String email, String role);
    Optional<UserEntity> getUserById (Long idUser) ;
    List<UserEntity> getUsers() ;

    String forgotPassword(String email) throws MessagingException;

    String setPassword(String email, String newpassword);
}

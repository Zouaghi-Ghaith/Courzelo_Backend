package com.example.corzello.Controller;

import com.example.corzello.Entity.AssociatedEntity;
import com.example.corzello.Entity.Role;
import com.example.corzello.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UserEntity user ;
    private String token ;
    private AssociatedEntity associatedEntity;
}

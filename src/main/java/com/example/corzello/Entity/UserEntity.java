package com.example.corzello.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String firstname ;
    private String lastname ;
    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private Set<Forum> forum;
    @Column(nullable=false, unique=true)
    String email;
    @Column(nullable=false)
    String password ;
    @Enumerated(EnumType.STRING)
    private Role roles ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //return List.of(new SimpleGrantedAuthority(roles.name()));
        List<GrantedAuthority> authorities = new ArrayList<>();
         authorities.add(new SimpleGrantedAuthority(roles.toString()));
         return authorities;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername() {

        return email;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String fullname(){return firstname+""+lastname;}

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Recruteur recruteur;
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Universite universite;
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Prof prof;
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Etudiant etudiant;

}


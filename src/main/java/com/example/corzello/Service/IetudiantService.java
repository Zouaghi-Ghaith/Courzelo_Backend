package com.example.corzello.Service;


import com.example.corzello.Entity.Etudiant;

import java.util.List;

public interface IetudiantService {
    public List<Etudiant> getAll();
    public Etudiant ajetudiant(Etudiant p );
}

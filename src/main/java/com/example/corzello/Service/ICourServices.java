package com.example.corzello.Service;

import com.example.corzello.Entity.Cours;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ICourServices {

 Cours addCours(Cours cours);

 Cours updateCours(Cours cours,long idCours);

 void deleteCour(long idCours);

 List<Cours> getAllCours();

}

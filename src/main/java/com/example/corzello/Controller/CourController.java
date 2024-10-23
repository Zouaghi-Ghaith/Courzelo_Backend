package com.example.corzello.Controller;


import com.example.corzello.Entity.*;
import com.example.corzello.Repository.*;
import com.example.corzello.Service.ICourServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/cour")
@CrossOrigin(origins = "*")
public class CourController {
    @Autowired
    private CourRepo courRepo;

    @Autowired
    private Iclassrepo classeRepo;

    @Autowired
    private Ietudiantrepository etudiantRepo;

    @Autowired
    private ModuleRepository moduleRepo;

    @Autowired
    private Iprofrepo profRepo;

    @Autowired
    ICourServices iCourServices;

  /*  @PostMapping("addCour")
    public Cours addCours(@RequestParam("filename") MultipartFile filename,@RequestBody Cours cours){

        return iCourServices.addCours(cours);

    }*/

    @PostMapping("/addCour")
    @CrossOrigin
    public Cours addCours(@RequestBody Cours cours){
        // Save Cours entity
        return courRepo.save(cours);
//       return  iCourServices.addCours(cours);

    }

    private String saveUploadedFile(MultipartFile courfile) throws IOException {
        String baseDir=System.getProperty("java.io.tmpdir");
        String fileName = UUID.randomUUID().toString() + "." + courfile.getOriginalFilename().split("\\.")[1];
        String filePath = baseDir+"/" + fileName; // Change to a secure server-side location
        courfile.transferTo(new File(filePath));
        return fileName; // Return the saved filename
    }

    @PutMapping("/updateCour/{idCours}")
    public Cours updateCours(  @RequestBody Cours cours , @PathVariable Long idCours){

        Cours course = courRepo.findById(idCours).get();

        System.out.print("nour"+course);
        course.setDetailsCour(cours.getDetailsCour());
        course.setNomCour(cours.getNomCour());
        course.setCourfile(cours.getCourfile());
        course.setModule(cours.getModule());
        course.setProgEducatif(cours.getProgEducatif());
        course.setCompteRendu(cours.getCompteRendu());
        courRepo.save(course);

        return course;
    }

    @DeleteMapping("/deleteCours/{idCours}")
    public  void deleteCour(@PathVariable("idCours") long idCours){
        iCourServices.deleteCour(idCours);
    }

    @GetMapping("/getAllCours")
    public List<Cours> getAllCours(){
//    	List<Cours> listCours = iCourServices.getAllCours();
        List<Cours> listCours = courRepo.findAllCoursWithModules();
        System.out.printf("test nour ",listCours);
        return iCourServices.getAllCours();
    }

    @GetMapping("/getAllModules")
    public Set<Module> getAllModules(){
        Set<Module> listMod=  moduleRepo.findAllModulesWithCourses();
        System.out.printf("test nour ",listMod);
        return listMod ;
    }

    @GetMapping("/getAllProfs")
    public Set<Prof> getAllProfs(){
        Set<Prof> listProf=  profRepo.findAllProfWithModules();
        System.out.printf("test nour ",listProf);
        return listProf ;
    }

    @GetMapping("/countCours")
    public Integer getCountCours(){
        List<Cours> listCours = iCourServices.getAllCours();
        System.out.printf("test nour ",listCours);
        return listCours.size();
    }

    @GetMapping("/countClasses")
    public Integer getCountClasses(){
        List<classManagement> listClasses = classeRepo.findAll();
        System.out.printf("list Classes",listClasses);
        return listClasses.size();
    }

    @GetMapping("/countProfs")
    public Integer getCountProfs(){
        List<Prof> listProfs = profRepo.findAll();
        System.out.printf("list profs",listProfs);
        return listProfs.size();
    }

    @GetMapping("/countModules")
    public Integer getCountModules(){
        List<ModuleEntity> listModules = (List<ModuleEntity>) moduleRepo.findAll();
        System.out.printf("list modules",listModules);
        return listModules.size();
    }

    @GetMapping("/countEtudiants")
    public Integer getCountEtudiants(){
        List<Etudiant> listEtudiants = etudiantRepo.findAll();
        System.out.printf("list etudiants",listEtudiants);
        return listEtudiants.size();
    }

    @GetMapping("/getById/{idCours}")
    public ResponseEntity<Cours> getCourById(@PathVariable Long idCours) {
        Optional<Cours> coursOptional = courRepo.findById(idCours);

        if (coursOptional.isPresent()) {
            return ResponseEntity.ok(coursOptional.get());
        } else {
            throw new NoSuchElementException("Cours with ID " + idCours + " not found.");
        }
    }


    /*
    @GetMapping("download/{courfile}")
    public ResponseEntity<Cours> downloadFiles(@PathVariable("courfile") String courfile) throws IOException {
        Path baseDirectory = Paths.get(DIRECTORY);
        Path filePath = baseDirectory.resolve(courfile).normalize();
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException("file was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", courfile);
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    } */

    @GetMapping("/download/{courfile}")
    @CrossOrigin(origins = "*")
    public byte[] getFileContent(@PathVariable("courfile") String courfile) {
        String baseDir=System.getProperty("java.io.tmpdir");
        try {
            // Read file content as bytes
            FileInputStream fis = new FileInputStream(baseDir+"/"+courfile);
            byte[] fileContent = fis.readAllBytes();
            fis.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Or handle the exception according to your needs
        }
    }




}

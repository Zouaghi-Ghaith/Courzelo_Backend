package com.example.corzello.Controller;

import com.example.corzello.Entity.GoogleSignIn;
import com.example.corzello.Entity.UserEntity;
import com.example.corzello.Service.UserService;
import jakarta.mail.MessagingException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userapi")
@CrossOrigin(origins = "http://localhost:4200")
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class UserController {
    @Autowired
    private UserService userService ;

    @GetMapping("/allusers")
    public ResponseEntity<List<UserEntity>> getUsers(){
        return  ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request ) throws MessagingException {
        return ResponseEntity.ok(userService.register(request)) ;
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request ){

        return ResponseEntity.ok(userService.authenticate(request)) ;
    }

    @PostMapping("/googleSignIn")
    public ResponseEntity<AuthenticationResponse> googleSignIn(@RequestBody GoogleSignIn request ){
        return ResponseEntity.ok(userService.googleSignIn(request.getEmail()));
    }
    @PostMapping("/role/addtoUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoletoUser(form.getEmail(), form.getRole());
        return  ResponseEntity.ok().build();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) throws MessagingException {
        return new ResponseEntity<>(userService.forgotPassword(email), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/set-password")
    public ResponseEntity<Map<String, String>> setPassword(@RequestBody ResetPassword resetter) {
        userService.setPassword(resetter.getEmail(), resetter.getNewpassword());

        // Construct a JSON object with a success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "New password set successfully");

        // Return the JSON object as the response body
        return ResponseEntity.ok(response);
    }


    @GetMapping("/accessDenied")
    public String denied(){
        return "accessDenied";
    }
}
@Data
class RoleToUserForm{
    private String email ;
    private String  role ;
}
@Data
class ResetPassword{
    private String email ;
    private String  newpassword ;
}
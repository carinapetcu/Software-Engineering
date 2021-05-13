package group2.cms.controller;

import group2.cms.controller.Request.CMSUserRequest;
import group2.cms.exceptions.BackendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import group2.cms.service.CMSUserService;

import java.util.Optional;

@RestController
public class CMSUserController {

    @Autowired
    private CMSUserService users;

    @PostMapping("users/add")
    public ResponseEntity<?> addUser(@RequestBody CMSUserRequest request) {
        try {
            return new ResponseEntity<>(
                    users.addUser(request.getFullName(), request.getEmail(),
                            request.getUsername(), request.getPassword()),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("users/update")
    public ResponseEntity<?> updateUser(@RequestBody CMSUserRequest request) {
        try {
            var id = request.getId();
            var fullName = request.getFullName();
            var email = request.getEmail();
            var username = request.getUsername();
            var password = request.getPassword();

            return new ResponseEntity<>(
                    users.updateUser(
                            id,
                            fullName.isBlank() ? Optional.empty() : Optional.of(fullName),
                            email.isBlank() ? Optional.empty() : Optional.of(email),
                            username.isBlank()? Optional.empty() : Optional.of(username),
                            password.isBlank()? Optional.empty() : Optional.of(password)),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("users/delete")
    public ResponseEntity<?> deleteUser(@RequestBody CMSUserRequest request){
        try{
            var userID = request.getId();
            users.deleteUser(userID);
            return new ResponseEntity<>(
                    "User with ID: " + userID + "deleted.",
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("users/list")
    public ResponseEntity<?> getAllUsers(){
        try{
            return new ResponseEntity<>(
                    users.getAllUsers(),
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("users/list/{userID}")
    public ResponseEntity<?> getUserByID(@PathVariable Long userID){
        try{
            return new ResponseEntity<>(
                    users.getUserByID(userID),
                    HttpStatus.OK
            );
        }catch (BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}

package group2.cms.controller;

import group2.cms.exceptions.BackendException;
import group2.cms.service.CMSUserService;
import group2.cms.service.DTO.CMSUser.CMSUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CMSUserController {

    @Autowired
    private CMSUserService users;

    @PostMapping("users/add")
    public ResponseEntity<?> addUser(@RequestBody CMSUserDTO userDTO) {
        try {
            return new ResponseEntity<>(
                    users.addUser(userDTO),
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
    public ResponseEntity<?> updateUser(@RequestBody CMSUserDTO userDTO) {
        try {
            return new ResponseEntity<>(
                    users.updateUser(userDTO),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("users/delete/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userID){
        try{
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

    @GetMapping("users/{userID}")
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

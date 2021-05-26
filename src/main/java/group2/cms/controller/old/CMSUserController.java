package group2.cms.controller.old;

import group2.cms.exceptions.BackendException;
import group2.cms.service.old.CMSUserService;
import group2.cms.service.DTO.CMSUser.CMSUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CMSUserController {

    @Autowired
    private CMSUserService users;

    @PostMapping("/user")
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

    @PutMapping("/user")
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

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            users.deleteUser(id);
            return new ResponseEntity<>(
                    "User with ID: " + id + "deleted.",
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<>(
                    users.getAllUsers(),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    users.getUserByID(id),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(
                    users.getUserByEmail(email),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(
                    users.getUserByUsername(username),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}

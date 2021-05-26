package group2.cms.controller;

import group2.cms.exceptions.*;
import group2.cms.service.DTO.Login.LoginRequest;
import group2.cms.service.DTO.SignUp.SignUpRequest;
import group2.cms.service.UserService;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try {
            var res = service.login(req);
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (InvalidCredentialsException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        } catch (ServerException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest req) {
        try {
            service.signup(req);
            return new ResponseEntity<>(
                    HttpStatus.CREATED
            );
        } catch (ValidationException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );
        } catch (CredentialsAlreadyInUseException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        } catch (ServerException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}

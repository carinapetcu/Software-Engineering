package group2.cms.service;


import group2.cms.domain.CMSUser;
import group2.cms.exceptions.CredentialsAlreadyInUseException;
import group2.cms.exceptions.InvalidCredentialsException;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CMSUserService {

    @Autowired
    private UserRepository userRepository;

    private void throwExceptionIfCredentialsAlreadyInUse(String username, String email) {
        var errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(
                userRepository.existsByUsername(username) ? "Username" + username + "already in use.\n" : ""
        ).append(
                userRepository.existsByEmail(email) ? "Email address" + email + "already in use.\n" : ""
        );

        if (errorMessageBuilder.length() != 0)
            throw new CredentialsAlreadyInUseException(errorMessageBuilder.toString());

    }


    public CMSUser addUser(String fullName,  String email, String username, String password) {
        throwExceptionIfCredentialsAlreadyInUse(username, email);
        return userRepository.save(new CMSUser(
                fullName,
                email,
                username,
                password
        ));
    }

    public CMSUser updateUser(Long userID, Optional<String> fullName, Optional<String> email, Optional<String> username,
                              Optional<String> password){
        var user = userRepository.findById(userID).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + userID)
        );

        throwExceptionIfCredentialsAlreadyInUse(
                username.orElse(""),
                email.orElse(""));

        fullName.ifPresent(user::setFullName);
        username.ifPresent(user::setUsername);
        email.ifPresent(user::setEmail);
        password.ifPresent(user::setPassword);

        return userRepository.save(user);
    }

    public void deleteUser(Long userID){
        userRepository.deleteById(userID);
    }

    public CMSUser getUserByID(Long userID){
        return userRepository.findById(userID).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + userID)
        );
    }

    public CMSUser getUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(
                () -> new InvalidCredentialsException("There is no user with the username: " + username)
        );
    }

    public CMSUser getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new InvalidCredentialsException("There is no user with the given email address: " + email)
        );
    }

    public List<CMSUser> getAllUsers(){
        return userRepository.findAll();
    }

}

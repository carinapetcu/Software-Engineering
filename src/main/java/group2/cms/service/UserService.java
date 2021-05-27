package group2.cms.service;

import group2.cms.domain.Conference;
import group2.cms.exceptions.CredentialsAlreadyInUseException;
import group2.cms.exceptions.InvalidCredentialsException;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.exceptions.ServerException;
import group2.cms.repository.ConferenceRepository;
import group2.cms.repository.UserRepository;
import group2.cms.service.DTO.CMSUser.CMSUserDTOConverter;
import group2.cms.service.DTO.Login.LoginRequest;
import group2.cms.service.DTO.Login.LoginResponse;
import group2.cms.service.DTO.SignUp.SignUpRequest;
import group2.cms.service.DTO.SignUp.SignupRequestConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ConferenceRepository conferenceRepo;
    @Autowired
    private SignupRequestConverter signupRequestConverter;
    @Autowired
    CMSUserDTOConverter cmsUserDTOConverter;

    private Long getConferenceByUserId(Long userId) {
        var conferences = conferenceRepo.findAll();
        for (Conference conf : conferences) {
            if (conf.getChair()
                    .getAuthor()
                    .getUser()
                    .getId()
                    .equals(userId)) {
                return conf.getId();
            }
        }
        return null;
    }

    public String getEmailOfUser(Long userId){
        var user = userRepo.findById(userId)
                .orElseThrow(() -> new InvalidIDException("User with given id does not exist."));
        return user.getEmail();
    }

    public LoginResponse login(LoginRequest data) {
        var user = userRepo.findByUsername(data.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("User with given username does not exist."));
        if (!user.getPassword().equals(data.getPassword())) {
            throw new InvalidCredentialsException("Wrong password.");
        }
        try {
            var confId = getConferenceByUserId(user.getId());
            return LoginResponse.builder()
                    .authority(null)
                    .userID(user.getId())
                    .conferenceID(confId)
                    .build();
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }

    public void signup(SignUpRequest data) {
        if (userRepo.existsByEmail(data.getEmail())) {
            throw new CredentialsAlreadyInUseException("Email address is already in use.");
        }
        if (userRepo.existsByUsername(data.getUsername())) {
            throw new CredentialsAlreadyInUseException("Username is already in use.");
        }
        var user = signupRequestConverter.dtoToEntity(data);
        try {
            userRepo.save(user);
        } catch (IllegalArgumentException e) {
            throw new ServerException(e.getMessage());
        }
    }
}

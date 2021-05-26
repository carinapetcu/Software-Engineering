package group2.cms.controller;

import group2.cms.exceptions.InvalidIDException;
import group2.cms.exceptions.ServerException;
import group2.cms.exceptions.ValidationException;
import group2.cms.service.ConferenceService;
import group2.cms.service.DTO.Conference.ConferenceRequest;
import group2.cms.service.DTO.Conference.ConferenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ConferenceController {
    @Autowired
    private ConferenceService service;

    @GetMapping("/conferences")
    public ResponseEntity<?> getConferences() {
        try {
            var res = service.getAll();
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/conference/{id}")
    public ResponseEntity<?> getConferenceById(@PathVariable Long id) {
        try {
            var res = service.getById(id);
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (InvalidIDException e) {
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

    @GetMapping("/conference/{id}/co_chairs")
    public ResponseEntity<?> getCoChairsOfConference(@PathVariable Long id) {
        try {
            var res = service.getCoChairsFromConference(id);

            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (InvalidIDException e) {
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

    @GetMapping("/conference/{id}/pc_members")
    public ResponseEntity<?> getPCMembersOfConference(@PathVariable Long id) {
        try {
            var res = service.getPCMembersFromConference(id);

            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (InvalidIDException e) {
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

    @GetMapping("/conference/{id}/reviewers")
    public ResponseEntity<?> getReviewersOfConferene(@PathVariable Long id) {
        try {
            var res = service.getReviewersFromConference(id);

            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (InvalidIDException e) {
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

    @PostMapping("/conference")
    public ResponseEntity<?> createConference(@RequestBody ConferenceRequest req) {
        try {
            var conferenceId = service.addConference(req);
            return new ResponseEntity<>(
                    conferenceId,
                    HttpStatus.CREATED
            );
        } catch (InvalidIDException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        } catch (ValidationException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY
            );
        } catch (ServerException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }
}

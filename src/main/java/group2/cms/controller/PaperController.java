package group2.cms.controller;

import group2.cms.exceptions.InvalidIDException;
import group2.cms.exceptions.ServerException;
import group2.cms.service.DTO.Paper.PaperRequest;
import group2.cms.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaperController {
    @Autowired
    private PaperService service;

    @GetMapping("/paper/review/{userId}")
    public ResponseEntity<?> getPapersForReview(@PathVariable Long userId) {
        try {
            var res = service.getPapersForReview(userId);
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (ServerException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/paper/{id}")
    public ResponseEntity<?> getPaper(@PathVariable Long id) {
        try {
            var res = service.getPaper(id);
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (InvalidIDException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (ServerException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/paper/{conferenceId}")
    public ResponseEntity<?> addPaper(@PathVariable Long conferenceId, @RequestBody PaperRequest req) {
        try {
            service.addPaperToConference(conferenceId, req);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InvalidIDException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (ServerException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("/papers/{conferenceId}")
    public ResponseEntity<?> getPapersFromConference(@PathVariable Long conferenceId) {
        try {
            var res = service.getPapersFromConference(conferenceId);
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );

        } catch (InvalidIDException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        } catch (ServerException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}

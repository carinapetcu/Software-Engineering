package group2.cms.controller.old;

import group2.cms.exceptions.BackendException;
import group2.cms.service.ConferenceService;
import group2.cms.service.DTO.Conference.ConferenceRequest;
import group2.cms.service.DTO.Conference.ConferenceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConferenceController {
    @Autowired
    private ConferenceService conferences;

    @PostMapping("/conference")
    public ResponseEntity<?> addConference(@RequestBody ConferenceRequest req) {
        try {
            var addedConference = conferences.addConference(req);
            return new ResponseEntity<>(
                    addedConference,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/conference")
    public ResponseEntity<?> updateConference(@RequestBody ConferenceResponse conferenceResponse) {
        try {
            var updatedConference = conferences.updateConference(conferenceResponse);
            return new ResponseEntity<>(
                    updatedConference,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @DeleteMapping("/conference/{id}")
    public ResponseEntity<?> deleteConference(@PathVariable Long id) {
        try {
            conferences.deleteConference(id);
            return new ResponseEntity<>(
                    "Conference deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/conferences")
    public ResponseEntity<?> getAllConferences() {
        return new ResponseEntity<>(
                conferences.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/conference/{id}")
    public ResponseEntity<?> getConference(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    conferences.getById(id),
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

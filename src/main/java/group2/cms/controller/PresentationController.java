package group2.cms.controller;

import group2.cms.service.DTO.PresentationRequest;
import group2.cms.exceptions.BackendException;
import group2.cms.service.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PresentationController {

    @Autowired
    private PresentationService presentations;

    @PostMapping("/presentation")
    public ResponseEntity<?> addPresentations(@RequestBody PresentationRequest presentationRequest) {
        try {
            var addedPresentation = presentations.addPresentation(presentationRequest.getPresentationID(),
                    presentationRequest.getPaper(),
                    presentationRequest.getSection(),
                    presentationRequest.getStartDate(),
                    presentationRequest.getEndDate(),
                    presentationRequest.getPresentation());
            return new ResponseEntity<>(
                    addedPresentation,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/presentation/{id}")
    public ResponseEntity<?> deletePresentations(@PathVariable Long id) {
        try {
            presentations.deletePresentation(id);
            return new ResponseEntity<>(
                    "Presentation deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/presentation")
    public ResponseEntity<?> getAllPresentations() {
        return new ResponseEntity<>(
                presentations.getAllPresentations(),
                HttpStatus.OK
        );
    }

    @GetMapping("/presentations/{id}")
    public ResponseEntity<?> getPresentation(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    presentations.getPresentationByID(id),
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

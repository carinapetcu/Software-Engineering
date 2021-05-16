package group2.cms.controller;

import group2.cms.exceptions.BackendException;
import group2.cms.service.ConferenceService;
import group2.cms.service.DTO.Conference.ConferenceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConferenceController{
    @Autowired
    private ConferenceService conferences;

    @PostMapping("conferences/add")
    public ResponseEntity<?> addConference(@RequestBody ConferenceDTO conferenceDTO){
        try{
            var addedConference = conferences.addConference(conferenceDTO);
            return new ResponseEntity<>(
                    addedConference,
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("conferences/update")
    public ResponseEntity<?> updateConference(@RequestBody ConferenceDTO conferenceDTO){
        try{
            var updatedConference = conferences.updateConference(conferenceDTO);
            return new ResponseEntity<>(
                    updatedConference,
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @DeleteMapping("conferences/delete")
    public ResponseEntity<?> deleteConference(@RequestBody ConferenceDTO conferenceDTO){
        try{
            conferences.deleteConference(conferenceDTO);
            return new ResponseEntity<>(
                    "Conference deleted",
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("conferences/list")
    public ResponseEntity<?> getAllConferences(){
        return new ResponseEntity<>(
                conferences.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("conferences/conference")
    public ResponseEntity<?> getConference(@RequestBody ConferenceDTO conferenceDTO){
        try{
            return new ResponseEntity<>(
                    conferences.getById(conferenceDTO),
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}

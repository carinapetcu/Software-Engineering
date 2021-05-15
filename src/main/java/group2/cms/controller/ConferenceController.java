package group2.cms.controller;

import group2.cms.service.DTO.ConferenceRequest;
import group2.cms.exceptions.BackendException;
import group2.cms.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConferenceController{
    @Autowired
    private ConferenceService conferences;

    @PostMapping("conferences/add")
    public ResponseEntity<?> addConference(@RequestBody ConferenceRequest conferenceRequest){
        try{
            var addedConference = conferences.addConference(conferenceRequest.getName(),
                    conferenceRequest.getEdition(),
                    conferenceRequest.getStartDate(),
                    conferenceRequest.getEndDate());
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

    @DeleteMapping("conferences/delete/{conferenceID}")
    public ResponseEntity<?> deleteConference(@PathVariable Long conferenceID){
        try{
            conferences.deleteConference(conferenceID);
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

}

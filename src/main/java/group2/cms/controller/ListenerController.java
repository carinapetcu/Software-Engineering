package group2.cms.controller;

import group2.cms.service.DTO.Listener.ListenerSectionDTO;
import group2.cms.service.DTO.ListenerRequest;
import group2.cms.exceptions.BackendException;
import group2.cms.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListenerController {
    @Autowired
    private ListenerService listenerService;

    @PostMapping("/listener")
    public ResponseEntity<?> addListener(@RequestBody ListenerRequest request) {
        try {
            var newListener = listenerService.addListener(
                    request.getListenerID()
            );
            return new ResponseEntity<>(
                    newListener,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/listener/{id}")
    public ResponseEntity<?> deleteListener(@PathVariable Long id) {
        try {
            listenerService.deleteListener(id);
            return new ResponseEntity<>(
                    "Listener deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/listeners")
    public ResponseEntity<?> getListeners() {
        return new ResponseEntity<>(
                listenerService.getAllListeners(),
                HttpStatus.OK
        );
    }

    @GetMapping("/listener/{id}")
    public ResponseEntity<?> getListener(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    listenerService.getListenerByID(id),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("/listener/section")
    public ResponseEntity<?> addSection(@RequestBody ListenerSectionDTO dto) {
        try {
            return new ResponseEntity<>(
                    listenerService.addNewSection(dto.getListenerId(), dto.getSectionId()),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/listeners/{sectionId}")
    public ResponseEntity<?> getListenersBySection(@PathVariable Long sectionId) {
        try {
            return new ResponseEntity<>(
                    listenerService.getAllListenersBySection(sectionId),
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

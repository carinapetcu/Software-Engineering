package group2.cms.controller.old;

import group2.cms.exceptions.BackendException;
import group2.cms.service.old.ChairService;
import group2.cms.service.DTO.Chair.ChairDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChairController {

    @Autowired
    private ChairService chairs;

    @PostMapping("/chair")
    public ResponseEntity<?> addChair(@RequestBody ChairDTO request) {
        try {
            var newChair = chairs.addChair(request);
            return new ResponseEntity<>(
                    newChair,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/chair")
    public ResponseEntity<?> updateChair(@RequestBody ChairDTO dto) {
        try {
//            var affiliation = request.getAffiliation();
//            var website = request.getWebsite();
//            var updateChair = chairs.updateChair(
//                    request.getUserID(),
//                    affiliation.isBlank() ? Optional.empty() : Optional.of(affiliation),
//                    website.isBlank() ? Optional.empty() : Optional.of(website)
//            );
            var res = chairs.updateChair(dto);
            return new ResponseEntity<>(
                    res,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/chair/{id}")
    public ResponseEntity<?> deleteChair(@PathVariable Long id) {
        try {
            chairs.deleteChair(id);
            return new ResponseEntity<>(
                    "Chair deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/chairs")
    public ResponseEntity<?> getChairs() {
        return new ResponseEntity<>(
                chairs.getAllChairs(),
                HttpStatus.OK
        );
    }

    @GetMapping("/chair/{id}")
    public ResponseEntity<?> getChair(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    chairs.getChairById(id),
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

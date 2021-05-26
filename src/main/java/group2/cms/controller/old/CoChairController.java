package group2.cms.controller.old;

import group2.cms.service.DTO.CoChair.CoChairResponse;
import group2.cms.exceptions.BackendException;
import group2.cms.service.old.CoChairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CoChairController {
    @Autowired
    private CoChairService coChairService;

    @PostMapping("/co_chair")
    public ResponseEntity<?> addCoChair(@RequestBody CoChairResponse req) {
        try {
            var res = coChairService.addCoChair(req);
            return new ResponseEntity<>(
                    newCoChair,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/co_chair")
    public ResponseEntity<?> updateCoChair(@RequestBody CoChairResponse req) {
        try {
            var affiliation = .getAffiliation();
            var website = request.getWebsite();
            var updateCoChair = coChairService.updateCoChair(
                    request.getUserID(),
                    affiliation.isBlank() ? Optional.empty() : Optional.of(affiliation),
                    website.isBlank() ? Optional.empty() : Optional.of(website)
            );
            return new ResponseEntity<>(
                    updateCoChair,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/co_chair/{id}")
    public ResponseEntity<?> deleteCoChair(@PathVariable Long id) {
        try {
            coChairService.deleteCoChair(id);
            return new ResponseEntity<>(
                    "CoChair deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/co_chairs")
    public ResponseEntity<?> getCoChairs() {
        return new ResponseEntity<>(
                coChairService.getAllCoChairs(),
                HttpStatus.OK
        );
    }

    @GetMapping("/co_chair/{id}")
    public ResponseEntity<?> getCoChair(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    coChairService.getCoChairById(id),
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

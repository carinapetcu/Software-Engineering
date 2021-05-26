package group2.cms.controller;

import group2.cms.service.DTO.ReviewRequest;
import group2.cms.exceptions.BackendException;
import group2.cms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviews;

    @PostMapping("/review")
    public ResponseEntity<?> addReviews(@RequestBody ReviewRequest reviewRequest) {
        try {
            var addedReview = reviews.addReview(reviewRequest.getReviewID(),
                    reviewRequest.getPcMember(),
                    reviewRequest.getPaper(),
                    reviewRequest.getFeedback(),
                    reviewRequest.getResult());
            return new ResponseEntity<>(
                    addedReview,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<?> deleteReviews(@PathVariable Long id) {
        try {
            reviews.deleteReview(id);
            return new ResponseEntity<>(
                    "Review deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getAllReviews() {
        return new ResponseEntity<>(
                reviews.getAllReviews(),
                HttpStatus.OK
        );
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<?> getReview(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    reviews.getReviewByID(id),
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

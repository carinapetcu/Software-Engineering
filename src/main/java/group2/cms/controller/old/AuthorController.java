package group2.cms.controller.old;


import group2.cms.exceptions.BackendException;
import group2.cms.service.old.AuthorService;
import group2.cms.service.DTO.Author.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authors;

    @PostMapping("/author")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            var addedAuthor = authors.addAuthor(authorDTO);
            return new ResponseEntity<>(
                    addedAuthor,
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        try {
            authors.deleteAuthor(id);
            return new ResponseEntity<>(
                    "Author deleted",
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<?> getAllAuthors() {
        return new ResponseEntity<>(
                authors.getAllAuthors(),
                HttpStatus.OK
        );
    }

    @GetMapping("/authors/{paperId}")
    public ResponseEntity<?> getAuthorsOfPaper(@PathVariable Long paperId) {
        try {
            return new ResponseEntity<>(
                    authors.getAuthorsOfPaper(paperId),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

    }

    @GetMapping("/author/{id}")
    public ResponseEntity<?> getAuthor(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    authors.getAuthorByID(id),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/author/{email}")
    public ResponseEntity<?> getAuthorByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(
                    authors.getAuthorByEmail(email),
                    HttpStatus.OK
            );
        } catch (BackendException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("/author/{username}")
    public ResponseEntity<?> getAuthorByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(
                    authors.getAuthorByUsername(username),
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

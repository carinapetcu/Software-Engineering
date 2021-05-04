package group2.cms.controller;


import group2.cms.controller.Request.AuthorRequest;
import group2.cms.domain.Author;
import group2.cms.exceptions.BackendException;
import group2.cms.service.AuthorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authors;

    @PostMapping("authors/add")
    public ResponseEntity<?> addAuthor(@RequestBody AuthorRequest authorRequest){
        try{
            var addedAuthor = authors.addAuthor(authorRequest.getUserID(), authorRequest.getPaperID());
            return new ResponseEntity<>(
                    addedAuthor,
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("authors/delete")
    public ResponseEntity<?> deleteAuthor(@RequestBody AuthorRequest authorRequest){
        try{
            authors.deleteAuthor(authorRequest.getUserID());
            return new ResponseEntity<>(
                    "Author deleted",
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("authors/list")
    public ResponseEntity<?> getAllAuthors(){
        return new ResponseEntity<>(
                authors.getAllAuthors(),
                HttpStatus.OK
        );
    }

    @GetMapping("authors/list/paper")
    public ResponseEntity<?> getAuthorsOfPaper(@RequestBody AuthorRequest authorRequest){
        try{
            return new ResponseEntity<>(
                    authors.getAuthorsOfPaper(authorRequest.getPaperID()),
                    HttpStatus.OK
            ) ;
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
       
    }

    @GetMapping("authors/{authorID}")
    public ResponseEntity<?> getAuthor(@PathVariable Long authorID){
        try{
            return new ResponseEntity<>(
                    authors.getAuthorByID(authorID),
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

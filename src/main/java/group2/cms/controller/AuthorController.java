package group2.cms.controller;


import group2.cms.controller.Request.AuthorRequest;
import group2.cms.domain.Author;
import group2.cms.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authors;

    @PostMapping("authors/add")
    public void addAuthor(@RequestBody AuthorRequest authorRequest){
        authors.addAuthor(authorRequest.getUserID(), authorRequest.getPaperID());
    }

    @DeleteMapping("authors/delete")
    public void deleteAuthor(@RequestBody AuthorRequest authorRequest){
        authors.deleteAuthor(authorRequest.getUserID());
    }

    @GetMapping("authors/list")
    public List<Author> getAllAuthors(){
        return authors.getAllAuthors();
    }

    @GetMapping("authors/list/paper")
    public List<Author> getAuthorsOfPaper(@RequestBody AuthorRequest authorRequest){
        return authors.getAuthorsOfPaper(authorRequest.getPaperID());
    }

    @GetMapping("authors/{authorID}")
    public Author getAuthor(@PathVariable Long authorID){
        return authors.getAuthorByID(authorID);
    }
}

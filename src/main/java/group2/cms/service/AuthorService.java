package group2.cms.service;

import group2.cms.domain.Author;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.AuthorRepository;
import group2.cms.repository.PaperRepository;
import group2.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(Long userID, Long paperID){
        var paperUploader = userRepository.findById(userID).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + userID)
        );
        var uploadedPaper = paperRepository.findById(paperID).orElseThrow(
                () -> new InvalidIDException("Invalid Paper ID: " + paperID)
        );

        return authorRepository.save(new Author(paperUploader, uploadedPaper));
    }

    public void deleteAuthor(Long authorID){
        authorRepository.deleteById(authorID);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsOfPaper(Long paperID){
        var paper = paperRepository.findById(paperID).orElseThrow(
                () -> new InvalidIDException("Invalid Paper ID: " + paperID)
        );
        return authorRepository.findAuthorsByPaper(paper);
    }

    public Author getAuthorByID(Long authorID){
        return authorRepository.findById(authorID).orElseThrow(
                () -> new InvalidIDException("Invalid Author ID: " + authorID)
        );
    }

    public Author getAuthorByEmail(String email){
        return authorRepository.findAuthorByEmail(email).orElseThrow(
                () -> new InvalidIDException("Invalid Author email address: " + email)
        );
    }

    public Author getAuthorByUsername(String username){
        return authorRepository.findAuthorByUsername(username).orElseThrow(
                () -> new InvalidIDException("Invalid Author Username: " + username)
        );
    }
}

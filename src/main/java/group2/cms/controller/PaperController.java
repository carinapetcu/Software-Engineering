package group2.cms.controller;

import group2.cms.service.DTO.PaperRequest;
import group2.cms.domain.Author;
import group2.cms.domain.Keyword;
import group2.cms.domain.Paper;
import group2.cms.exceptions.BackendException;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.service.AuthorService;
import group2.cms.service.KeywordService;
import group2.cms.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private KeywordService keywordService;

    @PostMapping("papers/add")
    public ResponseEntity<?> addPaper(@RequestBody PaperRequest paperRequest){
        try{

            List<Author> authors = paperRequest.getAuthorList()
                    .stream()
                    .map(authorId -> authorService.getAuthorByID(authorId))
                    .collect(Collectors.toList());

            Set<Keyword> keywords = paperRequest.getKeywordSet()
                    .stream()
                    .map(keyword -> {
                        try{
                            return keywordService.getKeyword(keyword);
                        }
                        catch(InvalidIDException e){
                            return keywordService.addKeyword(keyword);
                        }

                    })
                    .collect(Collectors.toSet());

            Paper paper = paperService.addPaper(paperRequest.getTitle(),
                    paperRequest.getPaperAbstract(),
                    authors,
                    keywords);

            return new ResponseEntity<>(
                    paper,
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("papers/delete/{paperId}")
    public ResponseEntity<?> deletePaper(@PathVariable Long paperId){
        try{
            paperService.deletePaper(paperId);
            return new ResponseEntity<>(
                    "Paper deleted",
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


    @GetMapping("papers/list")
    public ResponseEntity<?> getAllPapers(){
        return new ResponseEntity<>(
                paperService.getAllPapers(),
                HttpStatus.OK
        );
    }

    @GetMapping("papers/{paperId}")
    public ResponseEntity<?> getPaper(@PathVariable Long paperId){
        try{
            return new ResponseEntity<>(
                    paperService.getPaperById(paperId),
                    HttpStatus.OK
            );
        }catch(BackendException e){
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("papers/title/{title}")
    public ResponseEntity<?> getPaperByTitle(@PathVariable String title){
        try{
            return new ResponseEntity<>(
                    paperService.getPaperByTitle(title),
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

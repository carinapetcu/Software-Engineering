package group2.cms.service;

import group2.cms.domain.Author;
import group2.cms.domain.Keyword;
import group2.cms.domain.Paper;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.PaperRepository;
import group2.cms.service.DTO.Paper.PaperDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PaperDTOConverter converter;

    public Paper addPaper(String title, String paperAbstract, List<Author> authorList, Set<Keyword> keywordSet){
        return paperRepository.save(new Paper(title, paperAbstract, authorList, keywordSet));
    }

    public void deletePaper(Long paperId){
        paperRepository.deleteById(paperId);
    }

    public List<Paper> getAllPapers(){
        return paperRepository.findAll();
    }

    public Paper getPaperById(Long paperId){
        return paperRepository.findById(paperId)
                .orElseThrow(() -> new InvalidIDException("Invalid paperId " + paperId));
    }

    public Paper getPaperByTitle(String title){
        return paperRepository.findPaperByTitle(title)
                .orElseThrow(() -> new InvalidIDException("Invalid title " + title));
    }
}

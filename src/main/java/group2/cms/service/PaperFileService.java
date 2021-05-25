package group2.cms.service;

import group2.cms.repository.PaperFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperFileService {
    @Autowired
    private PaperFileRepository repository;
}

package group2.cms.service;

import group2.cms.domain.Conference;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class ConferenceService {
    @Autowired
    private ConferenceRepository repository;

    public Conference addConference(String name, String edition, LocalDate startDate, LocalDate endDate) {
        var conf = new Conference();
        conf.setName(name);
        conf.setEdition(edition);
        conf.setStartDate(startDate);
        conf.setEndDate(endDate);
        return repository.save(conf);
    }

    public void deleteConference(Long id) {
        repository.deleteById(id);
    }

    public Conference updateConference(Long id, String name, String edition, LocalDate startDate, LocalDate endDate) {
        var conf = repository.findById(id).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID:" + id)
        );
        conf.setName(name);
        conf.setEdition(edition);
        conf.setStartDate(startDate);
        conf.setEndDate(endDate);

        repository.deleteById(id);
        return repository.save(conf);
    }

    public Conference getById(Long id) {
        return repository.getOne(id);
    }

    public List<Conference> getAll(Long id) {
        return repository.findAll();
    }
}

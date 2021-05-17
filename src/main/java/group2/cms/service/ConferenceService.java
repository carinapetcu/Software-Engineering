package group2.cms.service;

import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.ConferenceRepository;
import group2.cms.service.DTO.Conference.ConferenceDTO;
import group2.cms.service.DTO.Conference.ConferenceDTOConverter;
import group2.cms.service.DTO.Conference.ConferencesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceDTOConverter converter;

    @Autowired
    private ConferenceRepository repository;

    public ConferenceDTO addConference(ConferenceDTO conferenceDTO) {
        var newConference = repository.save(converter.dtoToEntity(conferenceDTO));
        return converter.entityToDto(newConference);
    }

    public void deleteConference(Long conferenceID) {
        repository.deleteById(conferenceID);
    }

    public ConferenceDTO updateConference(ConferenceDTO conferenceDTO) {
        var id = conferenceDTO.getId();
        var conf = repository.findById(id).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID:" + id)
        );
        var name = conferenceDTO.getName();
        var edition = conferenceDTO.getEdition();
        var startDate = conferenceDTO.getStartDate();
        var endDate = conferenceDTO.getEndDate();

        if(name != null) conf.setName(name);
        if(edition != null) conf.setEdition(edition);
        if(startDate != null) conf.setStartDate(startDate);
        if(endDate != null) conf.setEndDate(endDate);

        return converter.entityToDto(repository.save(conf));
    }

    public ConferenceDTO getById(Long conferenceID)
    {
        var conference = repository.findById(conferenceID).orElseThrow(
                () -> new InvalidIDException("Invalid conference ID: " + conferenceID)
        );
        return converter.entityToDto(conference);
    }

    public ConferencesDTO getAll() {
        var conferencesDTO = new ConferencesDTO();

        repository.findAll().stream()
        .map(converter::entityToDto)
        .forEach(conferencesDTO::addDTO);

        return conferencesDTO;
    }
}

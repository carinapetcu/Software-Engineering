package group2.cms.service.DTO.Conference;

import group2.cms.domain.Conference;
import group2.cms.service.DTO.DTOConverter;
import org.springframework.stereotype.Component;

@Component
public class ConferenceDTOConverter implements DTOConverter<Conference, ConferenceDTO> {
    @Override
    public Conference dtoToEntity(ConferenceDTO dto) {
       var conference = new Conference(
                dto.getName(),
                dto.getEdition(),
                dto.getStartDate(),
                dto.getEndDate()
        );
       if(dto.getId() != null)
           conference.setId(dto.getId());
       return conference;
    }

    @Override
    public ConferenceDTO entityToDto(Conference conference) {
        return ConferenceDTO.builder()
                .id(conference.getId())
                .name(conference.getName())
                .edition(conference.getEdition())
                .startDate(conference.getStartDate())
                .endDate(conference.getEndDate())
                .build();
    }
}

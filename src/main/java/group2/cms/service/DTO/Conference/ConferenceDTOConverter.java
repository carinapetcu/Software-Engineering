package group2.cms.service.DTO.Conference;

import group2.cms.domain.Conference;
import group2.cms.service.DTO.DTOConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;

//@Component
//public class ConferenceDTOConverter implements DTOConverter<Conference, ConferenceResponse> {
//    @Override
//    public Conference dtoToEntity(ConferenceResponse dto) {
//       var conference = new Conference(
//                dto.getName(),
//                dto.getEdition(),
//                dto.getStartDate(),
//                dto.getEndDate()
//        );
//       if(dto.getId() != null)
//           conference.setId(dto.getId());
//       return conference;
//    }
//
//    @Override
//    public ConferenceResponse entityToDto(Conference conference) {
//        return ConferenceResponse.builder()
//                .id(conference.getId())
//                .name(conference.getName())
//                .edition(conference.getEdition())
//                .startDate(conference.getStartDate())
//                .endDate(conference.getEndDate())
//                .build();
//    }
//
//    @Override
//    public ConferencesResponse entitiesToDTO(Collection<Conference> conferences) {
//        var conferencesDTO = new ConferencesResponse();
//
//        conferences.stream()
//                .map(this::entityToDto)
//                .forEach(conferencesDTO::addDTO);
//
//        return conferencesDTO;
//    }
//}

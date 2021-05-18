package group2.cms.service.DTO.Paper;

import group2.cms.domain.Paper;
import group2.cms.service.DTO.DTOConverter;
import group2.cms.service.DTO.EntitiesDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PaperDTOConverter implements DTOConverter<Paper, PaperDTO> {
    @Override
    public Paper dtoToEntity(PaperDTO dto) {
        return null;
    }

    @Override
    public PaperDTO entityToDto(Paper entity) {
        return null;
    }

    @Override
    public EntitiesDTO<Paper, PaperDTO> entitiesToDTO(Collection<Paper> entities) {
        return null;
    }
}

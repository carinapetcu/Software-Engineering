package group2.cms.service.DTO.CoChair;

import group2.cms.domain.Authority;
import group2.cms.domain.PCMember;
import group2.cms.service.DTO.DTOConverter;
import group2.cms.service.DTO.EntitiesDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static group2.cms.domain.Authority.CoChair;

@Component
public class CoChairDTOConverter  {
//    @Override
//    public PCMember dtoToEntity(CoChairResponse dto) {
//        var pcMember = new PCMember();
//        pcMember.setAffiliation(dto.getAffiliation());
//        pcMember.setWebPage(dto.getAffiliation());
////        var cc = new CoChair();
////        cc.setAffiliation(dto.getAffiliation());
////        cc.setAuthority(CoChair);
////        cc.setId(dto.getUserID());
////        return cc;
//    }
//
//    @Override
//    public CoChairResponse entityToDto(CoChair entity) {
//        return CoChairResponse.builder()
//                .website(entity.getWebPage())
//                .userID(entity.getId())
//                .affiliation(entity.getAffiliation())
//                .build();
//    }
//
//    @Override
//    public EntitiesDTO<CoChair, CoChairResponse> entitiesToDTO(Collection<CoChair> entities) {
//        var dto = new CoChairsResponse();
//        entities.stream()
//                .map(this::entityToDto)
//                .forEach(dto::addDTO);
//        return dto;
//    }
}

package group2.cms.service;

import group2.cms.domain.Authority;
import group2.cms.domain.Chair;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.PCMemberRepository;
import group2.cms.repository.UserRepository;
import group2.cms.service.DTO.Chair.ChairDTO;
import group2.cms.service.DTO.Chair.ChairDTOConverter;
import group2.cms.service.DTO.Chair.ChairsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChairService {

    @Autowired
    private PCMemberRepository pcMemberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChairDTOConverter converter;

    public ChairDTO addChair(ChairDTO dto) {
        var user = userRepository.findById(dto.getUserID()).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + dto.getUserID())
        );
        return converter.entityToDto(pcMemberRepository.save(converter.dtoToEntity(dto)));
    }

    public void deleteChair(Long chairID) {
        try {
            pcMemberRepository.deleteById(chairID);
        } catch (EmptyResultDataAccessException | IllegalArgumentException e) {
            throw new InvalidIDException("Invalid Chair ID: " + chairID);
        }
    }

    public Chair updateChair(Long chairID, Optional<String> newAffiliation, Optional<String> newWebsite) {
        var pcMember = pcMemberRepository.findById(chairID).orElseThrow(
                () -> new InvalidIDException("Invalid Chair ID: " + chairID)
        );
        if (pcMember.getAuthority().compareTo(Authority.Chair) == 0) {
            var chair = (Chair) pcMember;
            newAffiliation.ifPresent(chair::setAffiliation);
            newWebsite.ifPresent(chair::setWebPage);
            return pcMemberRepository.save(chair);
        } else {
            throw new InvalidIDException("Invalid Chair ID: " + chairID);
        }
    }

    public ChairsDTO getAllChairs() {
        return converter.entitiesToDTO(pcMemberRepository.findAll()
                .stream()
                .filter(pcMember -> pcMember.getAuthority().compareTo(Authority.Chair) == 0)
                .map(pcMember -> (Chair) pcMember)
                .collect(Collectors.toList()));
    }

    public ChairDTO getChairById(Long chairID) {
        var pcMember = pcMemberRepository.findById(chairID).orElseThrow(
                () -> new InvalidIDException("Invalid Chair ID: " + chairID)
        );
        if (pcMember.getAuthority().compareTo(Authority.Chair) == 0) {
            return converter.entityToDto((Chair) pcMember);
        } else {
            throw new InvalidIDException("Invalid Chair ID: " + chairID);
        }
    }
}

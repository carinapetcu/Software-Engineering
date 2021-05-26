package group2.cms.service.old;

import group2.cms.domain.Authority;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.PCMemberRepository;
import group2.cms.repository.UserRepository;
import group2.cms.service.DTO.CoChair.CoChairResponse;
import group2.cms.service.DTO.CoChair.CoChairDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoChairService {
    @Autowired
    private PCMemberRepository pcMemberRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoChairDTOConverter converter;

    public CoChair addCoChair(CoChairResponse dto) {
        var user = userRepository.findById(dto.getUserID()).orElseThrow(
                () -> new InvalidIDException("Invalid User ID: " + dto.getUserID())
        );
        var chair = converter.dtoToEntity(dto);
        chair.setUsername(user.getUsername());
        chair.setAuthority();
        return pcMemberRepository.save();
    }

    public void deleteCoChair(Long coChairID) {
        try {
            pcMemberRepository.deleteById(coChairID);
        } catch (EmptyResultDataAccessException | IllegalArgumentException e) {
            throw new InvalidIDException("Invalid coChair ID: " + coChairID);
        }
    }

    public CoChair updateCoChair(Long coChairID, Optional<String> newAffiliation, Optional<String> newWebsite) {
        var pcMember = pcMemberRepository.findById(coChairID).orElseThrow(
                () -> new InvalidIDException("Invalid CoChair ID: " + coChairID)
        );
        if (pcMember.getAuthority().compareTo(Authority.CoChair) == 0) {
            var coChair = (CoChair) pcMember;
            newAffiliation.ifPresent(coChair::setAffiliation);
            newWebsite.ifPresent(coChair::setWebPage);
            return pcMemberRepository.save(coChair);
        } else {
            throw new InvalidIDException("Invalid CoChair ID: " + coChairID);
        }
    }

    public List<CoChair> getAllCoChairs() {
        return pcMemberRepository.findAll()
                .stream()
                .filter(pcMember -> pcMember.getAuthority().compareTo(Authority.CoChair) == 0)
                .map(pcMember -> (CoChair) pcMember)
                .collect(Collectors.toList());
    }

    public CoChair getCoChairById(Long coChairID) {
        var pcMember = pcMemberRepository.findById(coChairID).orElseThrow(
                () -> new InvalidIDException("Invalid CoChair ID: " + coChairID)
        );
        if (pcMember.getAuthority().compareTo(Authority.CoChair) == 0) {
            return (CoChair) pcMember;
        } else {
            throw new InvalidIDException("Invalid CoChair ID: " + coChairID);
        }
    }
}


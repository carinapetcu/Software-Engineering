package group2.cms.service;

import group2.cms.domain.Section;
import group2.cms.exceptions.InvalidIDException;
import group2.cms.repository.PCMemberRepository;
import group2.cms.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class SectionService {
    @Autowired
    private SectionRepository sectionRepository;
    private PCMemberRepository pcMemberRepository;

    public Section addSection(String room, Integer capacity, LocalDate startDate, LocalDate endDate, String theme, Long chairId) {
        var section = new Section();
        section.setRoom(room);
        section.setCapacity(capacity);
        section.setStartDate(startDate);
        section.setEndDate(endDate);
        section.setTheme(theme);
        section.setSessionChair(
                pcMemberRepository
                        .findById(chairId)
                        .orElseThrow(
                                () -> new InvalidIDException("Invalid member ID: " + chairId)
                        )
        );
        return sectionRepository.save(section);
    }

    public void deleteSection(Long id) {
        sectionRepository.deleteById(id);
    }

    public Section updateSection(Long id, String room, Integer capacity, LocalDate startDate, LocalDate endDate, String theme, Long chairId) {
        var section = sectionRepository.findById(id).orElseThrow(() -> new InvalidIDException("Invalid section ID: " + id));
        section.setRoom(room);
        section.setCapacity(capacity);
        section.setStartDate(startDate);
        section.setEndDate(endDate);
        section.setTheme(theme);
        section.setSessionChair(
                pcMemberRepository
                        .findById(chairId)
                        .orElseThrow(
                                () -> new InvalidIDException("Invalid member ID: " + chairId)
                        )
        );
        sectionRepository.deleteById(id);
        return sectionRepository.save(section);
    }

    public Section findById(Long id) {
        return sectionRepository.findById(id).orElseThrow(() -> new InvalidIDException("Invalid section id: " + id));
    }

    public List<Section> getAll() {
        return sectionRepository.findAll();
    }
}

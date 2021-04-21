package iss.core.repository;

import iss.core.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section,Long> {
}

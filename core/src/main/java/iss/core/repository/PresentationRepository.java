package iss.core.repository;

import iss.core.domain.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<Presentation,Long> {
}

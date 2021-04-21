package iss.core.repository;

import iss.core.domain.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference,Long> {
}

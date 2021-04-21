package iss.core.repository;

import iss.core.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaperRepository extends JpaRepository<Paper,Long> {
}

package iss.core.repository;

import iss.core.domain.Chair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChairRepository extends JpaRepository<Chair,Long> {
}

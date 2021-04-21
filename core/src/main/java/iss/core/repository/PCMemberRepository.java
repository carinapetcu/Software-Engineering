package iss.core.repository;

import iss.core.domain.PCMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PCMemberRepository extends JpaRepository<PCMember,Long> {
}

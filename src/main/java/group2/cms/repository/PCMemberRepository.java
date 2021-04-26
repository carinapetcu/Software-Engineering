package group2.cms.repository;

import group2.cms.domain.PCMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PCMemberRepository extends JpaRepository<PCMember,Long> {
}

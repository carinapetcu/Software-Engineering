package group2.cms.repository;

import group2.cms.domain.CMSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CMSUser,Long> {
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<CMSUser> findByUsername(String username);
    Optional<CMSUser> findByEmail(String email);
}

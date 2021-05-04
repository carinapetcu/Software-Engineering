package group2.cms.repository;

import group2.cms.domain.CMSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CMSUser,Long> {
}

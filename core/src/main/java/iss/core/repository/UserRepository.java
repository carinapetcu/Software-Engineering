package iss.core.repository;

import iss.core.domain.CMSUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CMSUser,Long> {
}

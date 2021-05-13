package group2.cms.repository;

import group2.cms.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    @Query(value = "select * from keyword k where k.keyword = ?1 limit 1", nativeQuery = true)
    Optional<Keyword> findKeyword(String keyword);
}

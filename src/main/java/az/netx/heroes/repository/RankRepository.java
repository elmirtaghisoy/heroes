package az.netx.heroes.repository;

import az.netx.heroes.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    List<Rank> findAllByStatus(String status);
}

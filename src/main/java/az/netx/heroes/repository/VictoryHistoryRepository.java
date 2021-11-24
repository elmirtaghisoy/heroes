package az.netx.heroes.repository;

import az.netx.heroes.entity.VictoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VictoryHistoryRepository extends JpaRepository<VictoryHistory, Long> {
    List<VictoryHistory> findTop2ByIdNotAndStatus(Long id, String active);
}

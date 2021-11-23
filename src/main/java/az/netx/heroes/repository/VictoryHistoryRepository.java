package az.netx.heroes.repository;

import az.netx.heroes.entity.VictoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VictoryHistoryRepository extends JpaRepository<VictoryHistory, Long> {
}

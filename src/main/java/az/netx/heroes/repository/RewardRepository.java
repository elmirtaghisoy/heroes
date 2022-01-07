package az.netx.heroes.repository;

import az.netx.heroes.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findAllByStatus(String status);

    List<Reward> findAllByIdNotInAndStatus(List<Long> ids, String status);

}

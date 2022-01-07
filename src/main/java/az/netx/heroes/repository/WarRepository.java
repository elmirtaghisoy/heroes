package az.netx.heroes.repository;

import az.netx.heroes.entity.War;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarRepository extends JpaRepository<War, Long> {

    List<War> findAllByStatus(String status);

    List<War> findAllByIdNotInAndStatus(List<Long> ids, String status);
}

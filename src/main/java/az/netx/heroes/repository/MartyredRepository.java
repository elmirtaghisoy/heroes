package az.netx.heroes.repository;

import az.netx.heroes.entity.Martyred;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MartyredRepository extends JpaRepository<Martyred, Long> {
    Page<Martyred> findAll(Specification<Martyred> martyredSpecification, Pageable pageRequest);
}

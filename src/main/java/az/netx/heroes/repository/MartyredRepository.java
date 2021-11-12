package az.netx.heroes.repository;

import az.netx.heroes.entity.Martyred;
import az.netx.heroes.model.response.MartyredResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MartyredRepository extends JpaRepository<Martyred, Long> {
    Page<MartyredResponse> findAll(Specification<Martyred> martyredSpecification, Pageable pageRequest);
}

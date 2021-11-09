package az.netx.heroes.repository;

import az.netx.heroes.entity.Hero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    Page<Hero> findAll(Specification<Hero> heroSpecification, Pageable pageRequest);
}

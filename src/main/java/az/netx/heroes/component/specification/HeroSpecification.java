package az.netx.heroes.component.specification;

import az.netx.heroes.component.criteria.HeroSearchCriteria;
import az.netx.heroes.entity.Hero;
import az.netx.heroes.entity.Hero_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HeroSpecification implements Specification<Hero> {

    private final HeroSearchCriteria request;

    public HeroSpecification(HeroSearchCriteria request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<Hero> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(root.get(Hero_.status), "ACTIVE"));

        if (Objects.nonNull(request)) {
            if (Objects.nonNull(request.getName())) {
                predicates.add(cb.like(root.get(Hero_.name), "%" + request.getName() + "%"));
            }
            if (Objects.nonNull(request.getSurname())) {
                predicates.add(cb.like(root.get(Hero_.surname), "%" + request.getSurname() + "%"));
            }
            if (Objects.nonNull(request.getFatherName())) {
                predicates.add(cb.like(root.get(Hero_.fatherName), "%" + request.getFatherName() + "%"));
            }
            if (Objects.nonNull(request.getBirthDate())) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get(Hero_.birthDate), LocalDate.parse(request.getBirthDate()))
                );
            }
            if (Objects.nonNull(request.getBirthDate())) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get(Hero_.birthDate), LocalDate.parse(request.getBirthDate()))
                );
            }
            if (Objects.nonNull(request.getRankId()) && request.getRankId() != -1) {
                predicates.add(
                        cb.equal(root.get(Hero_.rank), request.getRankId())
                );
            }
        }

        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}

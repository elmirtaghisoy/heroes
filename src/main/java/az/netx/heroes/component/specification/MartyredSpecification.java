package az.netx.heroes.component.specification;

import az.netx.heroes.component.criteria.MartyredSearchCriteria;
import az.netx.heroes.entity.Hero_;
import az.netx.heroes.entity.Martyred;
import az.netx.heroes.entity.Martyred_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MartyredSpecification implements Specification<Martyred> {

    private final MartyredSearchCriteria request;

    public MartyredSpecification(MartyredSearchCriteria request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<Martyred> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(root.get(Hero_.status), "ACTIVE"));

        if (Objects.nonNull(request)) {
            if (Objects.nonNull(request.getName())) {
                predicates.add(cb.like(root.get(Martyred_.name), "%" + request.getName() + "%"));
            }
            if (Objects.nonNull(request.getSurname())) {
                predicates.add(cb.like(root.get(Martyred_.surname), "%" + request.getSurname() + "%"));
            }
            if (Objects.nonNull(request.getFatherName())) {
                predicates.add(cb.like(root.get(Martyred_.fatherName), "%" + request.getFatherName() + "%"));
            }
            if (Objects.nonNull(request.getBirthDate())) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get(Martyred_.birthDate), LocalDate.parse(request.getBirthDate()))
                );
            }
            if (Objects.nonNull(request.getBirthDate())) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get(Martyred_.birthDate), LocalDate.parse(request.getBirthDate()))
                );
            }
            if (Objects.nonNull(request.getRankId()) && request.getRankId() != -1) {
                predicates.add(
                        cb.equal(root.get(Martyred_.rank), request.getRankId())
                );
            }
        }

        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}

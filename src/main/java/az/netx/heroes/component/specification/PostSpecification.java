package az.netx.heroes.component.specification;

import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.entity.Post;
import az.netx.heroes.entity.Post_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PostSpecification implements Specification<Post> {

    private PostSearchCriteria request;

    public PostSpecification(PostSearchCriteria request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(request)) {
            if (Objects.nonNull(request.getHeader())) {
                predicates.add(cb.like(root.get(Post_.header), "%" + request.getHeader() + "%"));
            }
            if (Objects.nonNull(request.getFromDate())) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get(Post_.createdDate), LocalDate.parse(request.getFromDate()).atStartOfDay())
                );
            }
            if (Objects.nonNull(request.getToDate())) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get(Post_.createdDate), LocalDate.parse(request.getToDate()).atStartOfDay())
                );
            }
            if (Objects.nonNull(request.getCategoryId()) && request.getCategoryId() != -1) {
                predicates.add(
                        cb.equal(root.get(Post_.category), request.getCategoryId())
                );
            }
        }
        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}

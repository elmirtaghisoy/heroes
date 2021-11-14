package az.netx.heroes.component.specification;

import az.netx.heroes.component.criteria.MessageSearchCriteria;
import az.netx.heroes.entity.Message;
import az.netx.heroes.entity.Message_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageSpecification implements Specification<Message> {

    private final MessageSearchCriteria request;

    public MessageSpecification(MessageSearchCriteria request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(request)) {

            if (Objects.nonNull(request.getStatus())) {
                if (request.getStatus().equals("ALL")) {
                    predicates.add(
                            cb.notEqual(root.get(Message_.status), "DELETED")
                    );
                } else {
                    predicates.add(
                            cb.equal(root.get(Message_.status), request.getStatus())
                    );
                }
            }

            if (Objects.nonNull(request.getEmail())) {
                predicates.add(cb.like(root.get(Message_.email), "%" + request.getEmail() + "%"));
            }
            if (Objects.nonNull(request.getReceivedDateFrom())) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get(Message_.receivedTs), LocalDate.parse(request.getReceivedDateFrom()).atStartOfDay())
                );
            }
            if (Objects.nonNull(request.getReceivedDateTo())) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get(Message_.receivedTs), LocalDate.parse(request.getReceivedDateTo()).atStartOfDay())
                );
            }
            if (Objects.nonNull(request.getReadDateFrom())) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get(Message_.receivedTs), LocalDate.parse(request.getReadDateFrom()).atStartOfDay())
                );
            }
            if (Objects.nonNull(request.getReadDateTo())) {
                predicates.add(
                        cb.lessThanOrEqualTo(root.get(Message_.receivedTs), LocalDate.parse(request.getReadDateTo()).atStartOfDay())
                );
            }
        }
        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}

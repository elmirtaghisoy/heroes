package az.netx.heroes.repository;

import az.netx.heroes.entity.Message;
import az.netx.heroes.model.response.MessageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<MessageResponse> findAll(Specification<Message> messageSpecification, Pageable pageRequest);
}

package az.netx.heroes.repository;

import az.netx.heroes.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUnread(boolean isUnread);
}

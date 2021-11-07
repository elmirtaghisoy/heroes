package az.netx.heroes.repository;

import az.netx.heroes.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByStatusTrue();

    Page<Post> findAll(Specification<Post> postSpecification, Pageable pageRequest);
}

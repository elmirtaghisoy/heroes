package az.netx.heroes.repository;

import az.netx.heroes.entity.Post;
import az.netx.heroes.model.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<PostResponse> findAll(Specification<Post> postSpecification, Pageable pageRequest);

}

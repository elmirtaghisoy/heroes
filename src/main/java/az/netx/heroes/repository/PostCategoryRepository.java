package az.netx.heroes.repository;

import az.netx.heroes.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory, Long> {
    List<PostCategory> findAllByStatus(String status);
}

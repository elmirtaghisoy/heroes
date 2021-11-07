package az.netx.heroes.component.query;

import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.component.specification.PostSpecification;
import az.netx.heroes.entity.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface SearchQueries {

    static Specification<Post> createPostSpecification(PostSearchCriteria request) {
        return new PostSpecification(request);
    }

}

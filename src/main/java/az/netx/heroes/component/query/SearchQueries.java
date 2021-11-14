package az.netx.heroes.component.query;

import az.netx.heroes.component.criteria.HeroSearchCriteria;
import az.netx.heroes.component.criteria.MartyredSearchCriteria;
import az.netx.heroes.component.criteria.MessageSearchCriteria;
import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.component.specification.HeroSpecification;
import az.netx.heroes.component.specification.MartyredSpecification;
import az.netx.heroes.component.specification.MessageSpecification;
import az.netx.heroes.component.specification.PostSpecification;
import az.netx.heroes.entity.Hero;
import az.netx.heroes.entity.Martyred;
import az.netx.heroes.entity.Message;
import az.netx.heroes.entity.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface SearchQueries {

    static Specification<Post> createPostSpecification(PostSearchCriteria request) {
        return new PostSpecification(request);
    }

    static Specification<Hero> createHeroSpecification(HeroSearchCriteria request) {
        return new HeroSpecification(request);
    }

    static Specification<Martyred> createMartyredSpecification(MartyredSearchCriteria request) {
        return new MartyredSpecification(request);
    }

    static Specification<Message> createMessageSpecification(MessageSearchCriteria request) {
        return new MessageSpecification(request);
    }
}

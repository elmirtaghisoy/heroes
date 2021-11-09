package az.netx.heroes.component.mapper;

import az.netx.heroes.entity.Contact;
import az.netx.heroes.entity.Message;
import az.netx.heroes.entity.Post;
import az.netx.heroes.entity.PostCategory;
import az.netx.heroes.entity.Rank;
import az.netx.heroes.entity.Reward;
import az.netx.heroes.entity.War;
import az.netx.heroes.model.request.ContactRequest;
import az.netx.heroes.model.request.MessageRequest;
import az.netx.heroes.model.request.PostCategoryRequest;
import az.netx.heroes.model.request.PostRequest;
import az.netx.heroes.model.request.RankRequest;
import az.netx.heroes.model.request.RewardRequest;
import az.netx.heroes.model.request.WarRequest;
import az.netx.heroes.model.response.ContactResponse;
import az.netx.heroes.model.response.MessageResponse;
import az.netx.heroes.model.response.PostCategoryResponse;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.model.response.RankResponse;
import az.netx.heroes.model.response.RewardResponse;
import az.netx.heroes.model.response.WarResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectMapper {

    War R2E(WarRequest request);

    WarResponse E2R(War entity);

    Reward R2E(RewardRequest request);

    RewardResponse E2R(Reward entity);

    Rank R2E(RankRequest request);

    RankResponse E2R(Rank entity);

    PostCategory R2E(PostCategoryRequest request);

    PostCategoryResponse E2R(PostCategory entity);

    Contact R2E(ContactRequest request);

    ContactResponse E2R(Contact entity);

    Message R2E(MessageRequest request);

    MessageResponse E2R(Message entity);

    Post R2E(PostRequest request);

    PostResponse E2R(Post entity);
}

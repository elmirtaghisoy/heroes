package az.netx.heroes.component.mapper;

import az.netx.heroes.entity.*;
import az.netx.heroes.model.request.*;
import az.netx.heroes.model.response.*;
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

package az.netx.heroes.component.mapper;

import az.netx.heroes.entity.Contact;
import az.netx.heroes.entity.File;
import az.netx.heroes.entity.Hero;
import az.netx.heroes.entity.Martyred;
import az.netx.heroes.entity.Message;
import az.netx.heroes.entity.Post;
import az.netx.heroes.entity.PostCategory;
import az.netx.heroes.entity.Rank;
import az.netx.heroes.entity.Reward;
import az.netx.heroes.entity.User;
import az.netx.heroes.entity.War;
import az.netx.heroes.model.request.ContactRequest;
import az.netx.heroes.model.request.HeroRequest;
import az.netx.heroes.model.request.MartyredRequest;
import az.netx.heroes.model.request.MessageRequest;
import az.netx.heroes.model.request.PostCategoryRequest;
import az.netx.heroes.model.request.PostRequest;
import az.netx.heroes.model.request.RankRequest;
import az.netx.heroes.model.request.RewardRequest;
import az.netx.heroes.model.request.UserAddRequest;
import az.netx.heroes.model.request.UserRequest;
import az.netx.heroes.model.request.WarRequest;
import az.netx.heroes.model.response.ContactResponse;
import az.netx.heroes.model.response.FileResponse;
import az.netx.heroes.model.response.HeroResponse;
import az.netx.heroes.model.response.MartyredResponse;
import az.netx.heroes.model.response.MessageResponse;
import az.netx.heroes.model.response.PostCategoryResponse;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.model.response.RankResponse;
import az.netx.heroes.model.response.RewardResponse;
import az.netx.heroes.model.response.UserResponse;
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

    Hero R2E(HeroRequest request);

    HeroResponse E2R(Hero entity);

    Martyred R2E(MartyredRequest request);

    MartyredResponse E2R(Martyred entity);

    RewardRequest R2R(RewardResponse response);

    WarRequest R2R(WarResponse response);

    FileResponse E2R(File file);

    UserResponse E2R(User entity);

    User R2E(UserRequest request);

    User AR2E(UserAddRequest request);
}

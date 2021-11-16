package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.PostCategory;
import az.netx.heroes.model.request.PostCategoryRequest;
import az.netx.heroes.model.response.PostCategoryResponse;
import az.netx.heroes.repository.PostCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostCategoryService {

    private final PostCategoryRepository postCategoryRepository;
    private final ObjectMapper objectMapper;

    public List<PostCategoryResponse> getAllPostCategory() {
        return postCategoryRepository.findAllByStatus("ACTIVE")
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public void createPostCategory(PostCategoryRequest request) {
        postCategoryRepository.save(objectMapper.R2E(request));
    }

    public void updatePostCategory(PostCategoryRequest request) {
        postCategoryRepository.save(objectMapper.R2E(request));
    }

    public void deletePostCategory(Long postCategoryId) {
        PostCategory entity = postCategoryRepository.getById(postCategoryId);
        entity.setStatus("DELETED");
        postCategoryRepository.save(entity);
    }

    public PostCategoryResponse getPostCategoryById(Long id) {
        return objectMapper.E2R(postCategoryRepository.getById(id));
    }
}

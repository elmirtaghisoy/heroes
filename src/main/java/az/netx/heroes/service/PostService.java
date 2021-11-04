package az.netx.heroes.service;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.entity.Post;
import az.netx.heroes.model.request.PostRequest;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ObjectMapper objectMapper;

    public List<PostResponse> getAllPost() {
        return postRepository.findAllByStatusTrue()
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public void createPost(PostRequest request) {
        postRepository.save(objectMapper.R2E(request));
    }

    public void updatePost(PostRequest request) {
        postRepository.save(objectMapper.R2E(request));
    }

    public void deletePost(Long postId) {
        Post entity = postRepository.getById(postId);
        entity.setStatus("DELETED");
        postRepository.save(entity);
    }
}

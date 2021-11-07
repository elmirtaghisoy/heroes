package az.netx.heroes.service;

import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.component.paging.Paging;
import az.netx.heroes.component.query.SearchQueries;
import az.netx.heroes.entity.Post;
import az.netx.heroes.model.request.PostRequest;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Paged<PostResponse> searchPosts(int page, int size, PostSearchCriteria searchRequest) {
        Pageable pageRequest = PageRequest.of(page - 1, size);
        Page<PostResponse> postPage = new PageImpl<>(
                postRepository.findAll(SearchQueries.createPostSpecification(searchRequest), pageRequest)
                        .stream()
                        .map(objectMapper::E2R)
                        .collect(Collectors.toList())
        );
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), page, size));
    }
}

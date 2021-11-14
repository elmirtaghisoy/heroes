package az.netx.heroes.service;

import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.component.paging.Paging;
import az.netx.heroes.component.query.SearchQueries;
import az.netx.heroes.entity.Post;
import az.netx.heroes.model.CustomFile;
import az.netx.heroes.model.request.PostRequest;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static az.netx.heroes.component.constraint.ApplicationConstraint.POST;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ObjectMapper objectMapper;

    public void createPost(PostRequest request) throws IOException {

        CustomFile file = CustomFile.builder()
                .category(POST)
                .file(request.getImg())
                .build();
        request.setFilePath(FileService.saveSingle(file));

        Post p = objectMapper.R2E(request);

        System.err.println(p.toString());
        System.err.println(p.getFilePath());

        postRepository.save(p);
    }

    public PostResponse getPostById(Long postId) {
        return objectMapper.E2R(postRepository.getById(postId));
    }

    public void updatePost(PostRequest request) throws IOException {

        if (request.getImg().isEmpty()) {
            request.setFilePath(request.getFilePath());
        } else {
            CustomFile file = CustomFile.builder()
                    .category(POST)
                    .file(request.getImg())
                    .build();
            request.setFilePath(FileService.saveSingle(file));
        }

        postRepository.save(objectMapper.R2E(request));
    }

    public void deletePost(Long postId) {
        Post entity = postRepository.getById(postId);
        entity.setStatus("DELETED");
        postRepository.save(entity);
    }

    public Paged<PostResponse> searchPost(int page, int size, PostSearchCriteria searchRequest) {
        Pageable pageRequest = PageRequest.of(page - 1, size);

        Page<PostResponse> postPage = postRepository.findAll(
                SearchQueries.createPostSpecification(searchRequest),
                pageRequest
        );

        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), page, size));
    }
}

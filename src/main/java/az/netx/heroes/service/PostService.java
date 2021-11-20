package az.netx.heroes.service;

import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.component.paging.Paging;
import az.netx.heroes.component.query.SearchQueries;
import az.netx.heroes.entity.File;
import az.netx.heroes.entity.Post;
import az.netx.heroes.model.CustomFile;
import az.netx.heroes.model.request.PostRequest;
import az.netx.heroes.model.response.FileResponse;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.repository.FileRepository;
import az.netx.heroes.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static az.netx.heroes.component.constraint.ApplicationConstraint.POST;
import static az.netx.heroes.component.constraint.ApplicationConstraint.POST_DEFAULT_IMG_PATH;
import static az.netx.heroes.config.MvcConfig.UPLOAD_PATH;
import static az.netx.heroes.util.FileUtil.generateRandomFolderName;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FileRepository fileRepository;
    private final ObjectMapper objectMapper;

    public Paged<PostResponse> searchPost(int page, int size, PostSearchCriteria searchRequest) {
        Pageable pageRequest = PageRequest.of(page - 1, size);

        Page<PostResponse> postPage = postRepository.findAll(
                SearchQueries.createPostSpecification(searchRequest),
                pageRequest
        );

        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), page, size));
    }

    public void createPost(PostRequest request) throws IOException {

        String uuidFolderName = generateRandomFolderName();
        request.setFolderUuid(uuidFolderName);

        if (request.getImg().isEmpty()) {
            request.setFilePath(POST_DEFAULT_IMG_PATH);
        } else {
            CustomFile file = CustomFile.builder()
                    .category(POST)
                    .file(request.getImg())
                    .build();
            request.setFilePath(FileService.saveSingle(file));
        }

        if (!request.getFiles().get(0).isEmpty()) {
            request.setFilePaths(FileService.saveMultiple(POST, uuidFolderName, request.getFiles()));
        }

        postRepository.save(objectMapper.R2E(request));
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

    public void deleteFileById(Long fileId) {
        File file = fileRepository.getById(fileId);
        FileService.deleteFile(file.getFolder().replace("/file", UPLOAD_PATH));
        fileRepository.deleteById(fileId);
    }

    public List<FileResponse> getFilesByObjId(Long objId) {
        return fileRepository.findAllByRefObjectId(objId).stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }

    public void saveAdditionalFiles(List<MultipartFile> files, Long id) throws IOException {
        if (files.get(0).getSize() != 0) {
            List<File> fileList = FileService.saveMultiple(POST, postRepository.getFolderUUID(id), files);
            fileList.forEach(file -> file.setRefObjectId(id));
            fileRepository.saveAll(fileList);
        }
    }

    public List<PostResponse> get4PostByNotId(Long id) {
        return postRepository.findTop4ByIdNotAndStatus(id, "ACTIVE")
                .stream()
                .map(objectMapper::E2R)
                .collect(Collectors.toList());
    }
}

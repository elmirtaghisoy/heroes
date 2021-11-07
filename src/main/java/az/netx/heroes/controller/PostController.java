package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/post")
public class PostController {

    private final PostService postService;
    private String ACCEPT_UUID;


    @GetMapping
    public Paged<PostResponse> getPostPage(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size,
            @RequestParam(value = "from", required = false, defaultValue = "2000-01-01") String fromDate,
            @RequestParam(value = "to", required = false, defaultValue = "2100-01-01") String toDate,
            @RequestParam(value = "header", required = false) String header,
            @RequestParam(value = "category", required = false) Long categoryId
    ) {
        var criteria = new PostSearchCriteria();
        criteria.setFromDate(fromDate);
        criteria.setToDate(toDate);
        criteria.setHeader(header);
        criteria.setCategoryId(categoryId);

        Paged<PostResponse> list = postService.searchPosts(
                page,
                size,
                criteria
        );

        return list;
    }
}

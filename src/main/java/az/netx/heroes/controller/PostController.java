package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.request.PostCategoryRequest;
import az.netx.heroes.model.request.PostRequest;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static az.netx.heroes.controller.ControllerConstraints.SUCCESS;
import static az.netx.heroes.util.SearchUtil.postSearchPathBuilder;

@Controller
@RequestMapping(value = "/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private String ACCEPT_UUID;

    @GetMapping
    public String getPostPage(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size,
            @RequestParam(value = "from", required = false, defaultValue = "2000-01-01") String fromDate,
            @RequestParam(value = "to", required = false, defaultValue = "2100-01-01") String toDate,
            @RequestParam(value = "header", required = false) String header,
            @RequestParam(value = "category", required = false) Long categoryId,
            HttpServletRequest request,
            Model model
    ) {
        var criteria = new PostSearchCriteria();
        criteria.setFromDate(fromDate);
        criteria.setToDate(toDate);
        criteria.setHeader(header);
        criteria.setCategoryId(categoryId);

        Paged<PostResponse> list = postService.searchPost(
                page,
                size,
                criteria
        );

        model.addAttribute("objectList", list);
        model.addAttribute("srcUrl", postSearchPathBuilder(request));

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        return "admin/post";
    }

    @GetMapping("/create-page")
    public String getCreatePage(Model model) {
        if (!model.containsAttribute("postRequest")) {
            model.addAttribute("postRequest", new PostRequest());
        }
        model.addAttribute("postCategoryRequest", new PostCategoryRequest());
        return "admin/createPostPage";
    }

    @GetMapping("/{id}")
    public String getById(
            @PathVariable(value = "id") Long id,
            Model model
    ) {
        if (!model.containsAttribute("postResponse")) {
            PostResponse response = postService.getPostById(id);
            model.addAttribute("postResponse", response);
        }
        return "admin/updatePostPage";
    }

    @PostMapping("/create")
    public String createPost(
            @Validated @ModelAttribute("postRequest") final PostRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postRequest", bindingResult);
            redirectAttributes.addFlashAttribute("postRequest", request);
            return "redirect:/post/create-page";
        }
        postService.createPost(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/post";
    }

    @PostMapping("/update")
    public String updatePost(
            @Validated @ModelAttribute("postRequest") final PostRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postResponse", bindingResult);
            redirectAttributes.addFlashAttribute("postResponse", request);
            return "redirect:/post/" + request.getId();
        }
        postService.updatePost(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/post";
    }

    @PostMapping("/delete")
    public String deletePost(
            @RequestParam("id") Long id,
            final RedirectAttributes redirectAttributes
    ) {
        postService.deletePost(id);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/post";
    }

}

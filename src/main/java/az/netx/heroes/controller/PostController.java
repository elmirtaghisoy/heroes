package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.PostSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.request.PostCategoryRequest;
import az.netx.heroes.model.request.PostRequest;
import az.netx.heroes.model.response.PostResponse;
import az.netx.heroes.service.PostCategoryService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static az.netx.heroes.controller.ControllerConstraints.SUCCESS;
import static az.netx.heroes.util.SearchUtil.postSearchPathBuilder;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostCategoryService postCategoryService;
    private String ACCEPT_UUID;

    @GetMapping("/admin/post")
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
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        return "admin/post";
    }

    @GetMapping("/admin/post/create-page")
    public String getCreatePage(Model model) {
        if (!model.containsAttribute("postRequest")) {
            model.addAttribute("postRequest", new PostRequest());
        }
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        model.addAttribute("postCategoryRequest", new PostCategoryRequest());
        return "admin/createPostPage";
    }

    @GetMapping("/admin/post/{id}")
    public String getById(
            @PathVariable(value = "id") Long id,
            Model model
    ) {
        if (!model.containsAttribute("postResponse")) {
            PostResponse response = postService.getPostById(id);
            model.addAttribute("postResponse", response);
        }
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "admin/updatePostPage";
    }

    @GetMapping(value = "/admin/post/get/delete")
    public String getById4Delete(
            @RequestParam("id") Long id,
            Model model
    ) {
        model.addAttribute("postResponse", postService.getPostById(id));
        return "admin/postRequestForm";
    }

    @GetMapping("/admin/post/{id}/file")
    public String getFilesByObjId(
            @PathVariable(value = "id") Long objId,
            Model model
    ) {
        model.addAttribute("files", postService.getFilesByObjId(objId));
        return "admin/files";
    }

    @PostMapping("/admin/post/create")
    public String createPost(
            @Validated @ModelAttribute("postRequest") final PostRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postRequest", bindingResult);
            redirectAttributes.addFlashAttribute("postRequest", request);
            return "redirect:/admin/post/create-page";
        }
        postService.createPost(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/post";
    }

    @PostMapping("/admin/post/update")
    public String updatePost(
            @Validated @ModelAttribute("postRequest") final PostRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postResponse", bindingResult);
            redirectAttributes.addFlashAttribute("postResponse", request);
            return "redirect:/admin/post/" + request.getId();
        }
        postService.updatePost(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/post";
    }

    @PostMapping("/admin/post/delete")
    public String deletePost(
            @RequestParam("id") Long id,
            final RedirectAttributes redirectAttributes
    ) {
        postService.deletePost(id);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/post";
    }

    @PostMapping("/post/file/delete")
    public String deleteFileById(
            @RequestParam(value = "id") Long fileId,
            @RequestParam(value = "objId") Long objId,
            final RedirectAttributes redirectAttributes
    ) {
        postService.deleteFileById(fileId);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/post/" + objId + "/file";
    }

    @PostMapping("/admin/post/{id}/file")
    public String saveFilesByObjId(
            @PathVariable("id") Long objId,
            @RequestParam("files") List<MultipartFile> files,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        postService.saveAdditionalFiles(files, objId);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/post/" + objId + "/file";
    }

    //CLIENT

    @GetMapping("/cl/post/{category}")
    public String clientGetPost(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size,
            @RequestParam(value = "from", required = false, defaultValue = "2000-01-01") String fromDate,
            @RequestParam(value = "to", required = false, defaultValue = "2100-01-01") String toDate,
            @RequestParam(value = "header", required = false) String header,
            @RequestParam(value = "category", required = false) Long categoryParam,
            @PathVariable(value = "category", required = false) Long categoryVariable,
            HttpServletRequest request,
            Model model
    ) {
        Long categoryId;

        if (categoryVariable.equals(-1L)) {
            categoryId = categoryParam;
        } else {
            categoryId = categoryVariable;
        }

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
        model.addAttribute("currentCategory", postCategoryService.getPostCategoryById(categoryId));
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());

        return "client/post";
    }

    @GetMapping("/cl/post/id/{id}")
    public String clientGetPostById(
            @PathVariable(value = "id") Long id,
            Model model
    ) {
        model.addAttribute("otherPosts", postService.get4PostByNotId(id));
        model.addAttribute("post", postService.getPostById(id));
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/onePost";
    }
}

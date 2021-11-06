package az.netx.heroes.controller;

import az.netx.heroes.model.request.PostCategoryRequest;
import az.netx.heroes.model.request.RankRequest;
import az.netx.heroes.model.request.RewardRequest;
import az.netx.heroes.model.request.WarRequest;
import az.netx.heroes.service.PostCategoryService;
import az.netx.heroes.service.RankService;
import az.netx.heroes.service.RewardService;
import az.netx.heroes.service.WarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping(value = "/categorization")
@RequiredArgsConstructor
public class CategorizationController implements ControllerConstraints {

    private final WarService warService;
    private final RankService rankService;
    private final PostCategoryService postCategoryService;
    private final RewardService rewardService;

    @GetMapping
    public String getCategorizationPage(
            Model model
    ) {
        if (!model.containsAttribute("warRequest")) {
            model.addAttribute("warRequest", new WarRequest());
        }
        model.addAttribute("wars", warService.getAllWar());
        model.addAttribute("ranks", rankService.getAllRank());
        model.addAttribute("rewards", rewardService.getAllReward());
        model.addAttribute("postCategories", postCategoryService.getAllPostCategory());

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }
        return "admin/category";
    }

/////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value = "/war/create")
    public String createWar(
            @Validated @ModelAttribute("warRequest") final WarRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.warRequest", bindingResult);
            redirectAttributes.addFlashAttribute("warRequest", request);
            return "redirect:/categorization";
        }
        warService.createWar(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/categorization";
    }

    @PostMapping(value = "/rank/create")
    public String createRank(RankRequest request) {
        rankService.createRank(request);
        return "redirect:/categorization";
    }

    @PostMapping(value = "/post-category/create")
    public String createPostCategory(PostCategoryRequest request) {
        postCategoryService.createPostCategory(request);
        return "admin/category";
    }

    @PostMapping(value = "/reward/create")
    public String createReward(RewardRequest request) {
        rewardService.createReward(request);
        return "admin/category";
    }

    /////////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/war")
    public String getWar(
            @RequestParam("id") Long id,
            Model model
    ) {
        model.addAttribute("warResponse", warService.getWar(id));
        model.addAttribute("uuid", UUID.randomUUID().toString());
        return "admin/categoryForm";
    }
/////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value = "/war/update")
    public String updateWar(
            @Validated @ModelAttribute("warRequest") final WarRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.warRequest", bindingResult);
            redirectAttributes.addFlashAttribute("warRequest", request);
            return "redirect:/categorization";
        }
        warService.updateWar(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/categorization";
    }

    @PostMapping(value = "/rank/update")
    public String updateRank(RankRequest request) {
        rankService.updateRank(request);
        return "admin/category";
    }

    @PostMapping(value = "/post-category/update")
    public String updatePostCategory(PostCategoryRequest request) {
        postCategoryService.updatePostCategory(request);
        return "admin/category";
    }

    @PostMapping(value = "/reward/update")
    public String updateReward(RewardRequest request) {
        rewardService.updateReward(request);
        return "admin/category";
    }

/////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value = "/war/delete")
    public String deleteWar(
            @RequestParam("id") Long warId,
            final RedirectAttributes redirectAttributes
    ) {
        warService.deleteWar(warId);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/categorization";
    }

    @PostMapping(value = "/rank/delete")
    public String deleteRank(@RequestParam("id") Long rankId) {
        rankService.deleteRank(rankId);
        return "admin/category";
    }

    @PostMapping(value = "/post-category/delete")
    public String deletePostCategory(@RequestParam("id") Long postCategoryId) {
        postCategoryService.deletePostCategory(postCategoryId);
        return "admin/category";
    }

    @PostMapping(value = "/reward/delete")
    public String deleteReward(@RequestParam("id") Long rewardId) {
        rewardService.deleteReward(rewardId);
        return "admin/category";
    }

}

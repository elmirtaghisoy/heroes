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
@RequestMapping(value = "/admin/categorization")
@RequiredArgsConstructor
public class CategorizationController implements ControllerConstraints {

    private final WarService warService;
    private final RankService rankService;
    private final PostCategoryService postCategoryService;
    private final RewardService rewardService;
    private String ACCEPT_UUID;

    @GetMapping
    public String getCategorizationPageAdmin(
            Model model
    ) {
        if (!model.containsAttribute("warRequest")) {
            model.addAttribute("warRequest", new WarRequest());
        }

        if (!model.containsAttribute("rankRequest")) {
            model.addAttribute("rankRequest", new RankRequest());
        }

        if (!model.containsAttribute("postCategoryRequest")) {
            model.addAttribute("postCategoryRequest", new PostCategoryRequest());
        }

        if (!model.containsAttribute("rewardRequest")) {
            model.addAttribute("rewardRequest", new RewardRequest());
        }

        model.addAttribute("wars", warService.getAllWar());
        model.addAttribute("ranks", rankService.getAllRank());
        model.addAttribute("rewards", rewardService.getAllReward());
        model.addAttribute("postCategories", postCategoryService.getAllPostCategory());

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }
        if (model.containsAttribute("error")) {
            model.addAttribute("error");
        }

        return "admin/category";
    }

    @PostMapping(value = "/war/create")
    public String createWarAdmin(
            @Validated @ModelAttribute("warRequest") final WarRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.warRequest", bindingResult);
            redirectAttributes.addFlashAttribute("warRequest", request);
            return "redirect:/admin/categorization";
        }
        warService.createWar(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/rank/create")
    public String createRankAdmin(
            @Validated @ModelAttribute("rankRequest") final RankRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rankRequest", bindingResult);
            redirectAttributes.addFlashAttribute("rankRequest", request);
            return "redirect:/admin/categorization";
        }
        rankService.createRank(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/post-category/create")
    public String createPostCategoryAdmin(
            @Validated @ModelAttribute("postCategoryRequest") final PostCategoryRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postCategoryRequest", bindingResult);
            redirectAttributes.addFlashAttribute("postCategoryRequest", request);
            return "redirect:/admin/categorization";
        }
        postCategoryService.createPostCategory(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/reward/create")
    public String createRewardAdmin(
            @Validated @ModelAttribute("rewardRequest") final RewardRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rewardRequest", bindingResult);
            redirectAttributes.addFlashAttribute("rewardRequest", request);
            return "redirect:/admin/categorization";
        }
        rewardService.createReward(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/categorization";
    }

    @GetMapping(value = "/war")
    public String getWarAdmin(
            @RequestParam("id") Long id,
            Model model
    ) {
        ACCEPT_UUID = UUID.randomUUID().toString();
        model.addAttribute("warResponse", warService.getWarById(id));
        model.addAttribute("uuid", ACCEPT_UUID);
        return "admin/warRequestForm";
    }

    @GetMapping(value = "/rank")
    public String getRankAdmin(
            @RequestParam("id") Long id,
            Model model
    ) {
        ACCEPT_UUID = UUID.randomUUID().toString();
        model.addAttribute("rankResponse", rankService.getRankById(id));
        model.addAttribute("uuid", ACCEPT_UUID);
        return "admin/rankRequestForm";
    }

    @GetMapping(value = "/post-category")
    public String getPostCategoryAdmin(
            @RequestParam("id") Long id,
            Model model
    ) {
        ACCEPT_UUID = UUID.randomUUID().toString();
        model.addAttribute("postCategoryResponse", postCategoryService.getPostCategoryById(id));
        model.addAttribute("uuid", ACCEPT_UUID);
        return "admin/postCategoryRequestForm";
    }

    @GetMapping(value = "/reward")
    public String getRewardAdmin(
            @RequestParam("id") Long id,
            Model model
    ) {
        ACCEPT_UUID = UUID.randomUUID().toString();
        model.addAttribute("rewardResponse", rewardService.getRewardById(id));
        model.addAttribute("uuid", ACCEPT_UUID);
        return "admin/rewardRequestForm";
    }

    @PostMapping(value = "/war/update")
    public String updateWarAdmin(
            @Validated @ModelAttribute("warRequest") final WarRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.warRequest", bindingResult);
            redirectAttributes.addFlashAttribute("warRequest", request);
            return "redirect:/admin/categorization";
        }
        warService.updateWar(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/rank/update")
    public String updateRankAdmin(
            @Validated @ModelAttribute("rankRequest") final RankRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rankRequest", bindingResult);
            redirectAttributes.addFlashAttribute("rankRequest", request);
            return "redirect:/admin/categorization";
        }
        rankService.updateRank(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/post-category/update")
    public String updatePostCategoryAdmin(
            @Validated @ModelAttribute("postCategoryRequest") final PostCategoryRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.postCategoryRequest", bindingResult);
            redirectAttributes.addFlashAttribute("postCategoryRequest", request);
            return "redirect:/admin/categorization";
        }
        postCategoryService.updatePostCategory(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/reward/update")
    public String updateRewardAdmin(
            @Validated @ModelAttribute("rewardRequest") final RewardRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rewardRequest", bindingResult);
            redirectAttributes.addFlashAttribute("rewardRequest", request);
            return "redirect:/admin/categorization";
        }
        rewardService.updateReward(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/war/delete")
    public String deleteWarAdmin(
            @RequestParam("id") Long warId,
            @RequestParam("uuid") String uuid,
            final RedirectAttributes redirectAttributes
    ) {
        if (uuid.equals(ACCEPT_UUID)) {
            warService.deleteWar(warId);
            redirectAttributes.addFlashAttribute("success", SUCCESS);
            return "redirect:/admin/categorization";
        }
        redirectAttributes.addFlashAttribute("error", UUID_ERROR);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/rank/delete")
    public String deleteRankAdmin(
            @RequestParam("id") Long rankId,
            @RequestParam("uuid") String uuid,
            final RedirectAttributes redirectAttributes
    ) {
        if (uuid.equals(ACCEPT_UUID)) {
            rankService.deleteRank(rankId);
            redirectAttributes.addFlashAttribute("success", SUCCESS);
            return "redirect:/admin/categorization";
        }
        redirectAttributes.addFlashAttribute("error", UUID_ERROR);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/post-category/delete")
    public String deletePostCategoryAdmin(
            @RequestParam("id") Long postCategoryId,
            @RequestParam("uuid") String uuid,
            final RedirectAttributes redirectAttributes
    ) {
        if (uuid.equals(ACCEPT_UUID)) {
            postCategoryService.deletePostCategory(postCategoryId);
            redirectAttributes.addFlashAttribute("success", SUCCESS);
            return "redirect:/admin/categorization";
        }
        redirectAttributes.addFlashAttribute("error", UUID_ERROR);
        return "redirect:/admin/categorization";
    }

    @PostMapping(value = "/reward/delete")
    public String deleteRewardAdmin(
            @RequestParam("id") Long rewardId,
            @RequestParam("uuid") String uuid,
            final RedirectAttributes redirectAttributes
    ) {
        if (uuid.equals(ACCEPT_UUID)) {
            rewardService.deleteReward(rewardId);
            redirectAttributes.addFlashAttribute("success", SUCCESS);
            return "redirect:/admin/categorization";
        }
        redirectAttributes.addFlashAttribute("error", UUID_ERROR);
        return "redirect:/admin/categorization";
    }

}

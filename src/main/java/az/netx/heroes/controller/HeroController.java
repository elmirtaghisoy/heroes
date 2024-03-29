package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.HeroSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.request.HeroRequest;
import az.netx.heroes.model.request.RankRequest;
import az.netx.heroes.model.response.HeroResponse;
import az.netx.heroes.model.response.RewardResponse;
import az.netx.heroes.model.response.WarResponse;
import az.netx.heroes.service.HeroService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

import static az.netx.heroes.controller.ControllerConstraints.SUCCESS;
import static az.netx.heroes.util.SearchUtil.heroSearchPathBuilder;
import static az.netx.heroes.util.SearchUtil.martyredSearchPathBuilder;

@Controller
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;
    private final RankService rankService;
    private final RewardService rewardService;
    private final WarService warService;
    private final PostCategoryService postCategoryService;

    @GetMapping("/admin/hero")
    public String getHeroPageAdmin(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "fatherName", required = false) String fatherName,
            @RequestParam(value = "rank", required = false) Long rankId,
            @RequestParam(value = "war", required = false) Long warId,
            @RequestParam(value = "reward", required = false) Long rewardId,
            HttpServletRequest request,
            Model model
    ) {
        var criteria = new HeroSearchCriteria();
        criteria.setName(name);
        criteria.setSurname(surname);
        criteria.setFatherName(fatherName);
        criteria.setRankId(rankId);
        criteria.setWarId(warId);
        criteria.setRewardId(rewardId);

        Paged<HeroResponse> list = heroService.searchHero(
                page,
                size,
                criteria
        );

        model.addAttribute("objectList", list);
        model.addAttribute("srcUrl", heroSearchPathBuilder(request));

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        return "admin/hero";
    }

    @GetMapping("/admin/hero/create-page")
    public String getCreatePageAdmin(
            Model model
    ) {
        if (!model.containsAttribute("heroRequest")) {
            model.addAttribute("heroRequest", new HeroRequest());
        }
        model.addAttribute("rankList", rankService.getAllRank());
        model.addAttribute("warList", warService.getAllWar());
        model.addAttribute("rewardList", rewardService.getAllReward());
        model.addAttribute("rankRequest", new RankRequest());
        return "admin/createHeroPage";
    }

    @GetMapping("/admin/hero/{id}")
    public String getByIdAdmin(
            @PathVariable(value = "id") Long heroId,
            Model model
    ) {
        if (!model.containsAttribute("heroResponse")) {
            HeroResponse response = heroService.getHeroById(heroId);
            model.addAttribute("rankList", rankService.getAllRank());
            model.addAttribute("heroResponse", response);

            if (response.getWars().isEmpty()) {
                model.addAttribute("warList", warService.getAllWar());
            } else {
                model.addAttribute("warList", warService.findWarByNotInIds(
                        response.getWars().stream().map(WarResponse::getId).collect(Collectors.toList())
                ));
            }

            if (response.getRewards().isEmpty()) {
                model.addAttribute("rewardList", rewardService.getAllReward());
            } else {
                model.addAttribute("rewardList", rewardService.findRewardByNotInIds(
                        response.getRewards().stream().map(RewardResponse::getId).collect(Collectors.toList())
                ));
            }

        }
        return "admin/updateHeroPage";
    }

    @GetMapping(value = "/admin/hero/get/delete")
    public String getById4DeleteAdmin(
            @RequestParam("id") Long id,
            Model model
    ) {
        model.addAttribute("heroResponse", heroService.getHeroById(id));
        return "admin/heroRequestForm";
    }

    @PostMapping("/admin/hero/create")
    public String createHeroAdmin(
            @Validated @ModelAttribute("heroRequest") final HeroRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroRequest", bindingResult);
            redirectAttributes.addFlashAttribute("heroRequest", request);
            return "redirect:/admin/hero/create-page";
        }
        heroService.createHero(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/hero";
    }

    @PostMapping("/admin/hero/update")
    public String updateHeroAdmin(
            @Validated @ModelAttribute("heroRequest") final HeroRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroResponse", bindingResult);
            redirectAttributes.addFlashAttribute("heroResponse", request);
            return "redirect:/admin/hero/" + request.getId();
        }
        heroService.updateHero(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/hero";
    }

    @PostMapping("/admin/hero/delete")
    public String deleteHeroAdmin(
            @RequestParam("id") Long heroId,
            final RedirectAttributes redirectAttributes
    ) {
        heroService.deleteHero(heroId);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/hero";
    }

    //CLIENT
    @GetMapping("/hero")
    public String clientGetHero(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "9") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "fatherName", required = false) String fatherName,
            @RequestParam(value = "rank", required = false) Long rankId,
            @RequestParam(value = "war", required = false) Long warId,
            @RequestParam(value = "reward", required = false) Long rewardId,
            HttpServletRequest request,
            Model model
    ) {
        var criteria = new HeroSearchCriteria();
        criteria.setName(name);
        criteria.setSurname(surname);
        criteria.setFatherName(fatherName);
        criteria.setRankId(rankId);
        criteria.setWarId(warId);
        criteria.setRewardId(rewardId);

        Paged<HeroResponse> list = heroService.searchHero(
                page,
                size,
                criteria
        );


        model.addAttribute("wars", warService.getAllWar());
        model.addAttribute("rewards", rewardService.getAllReward());
        model.addAttribute("ranks", rankService.getAllRank());
        model.addAttribute("objectList", list);
        model.addAttribute("srcUrl", martyredSearchPathBuilder(request));
        model.addAttribute("ranks", rankService.getAllRank());
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());

        return "client/hero";
    }

    @GetMapping("/hero/{id}")
    public String clientGetHeroById(
            @PathVariable(value = "id") Long id,
            Model model
    ) {
        model.addAttribute("hero", heroService.getHeroById(id));
        model.addAttribute("heroList", heroService.get3HeroByNotId(id));
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/oneHero";
    }
}

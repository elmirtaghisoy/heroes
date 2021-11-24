package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.MartyredSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.request.MartyredRequest;
import az.netx.heroes.model.request.RankRequest;
import az.netx.heroes.model.response.MartyredResponse;
import az.netx.heroes.model.response.RewardResponse;
import az.netx.heroes.model.response.WarResponse;
import az.netx.heroes.service.MartyredService;
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
import static az.netx.heroes.util.SearchUtil.martyredSearchPathBuilder;

@Controller
@RequiredArgsConstructor
public class MartyredController {

    private final MartyredService martyredService;
    private final RankService rankService;
    private final RewardService rewardService;
    private final WarService warService;
    private final PostCategoryService postCategoryService;

    @GetMapping("/martyred")
    public String getMartyredPage(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "8") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "birthDate", required = false) String birthDate,
            @RequestParam(value = "fatherName", required = false) String fatherName,
            @RequestParam(value = "rank", required = false) Long rankId,
            HttpServletRequest request,
            Model model
    ) {
        var criteria = new MartyredSearchCriteria();
        criteria.setName(name);
        criteria.setSurname(surname);
        criteria.setFatherName(fatherName);
        criteria.setBirthDate(birthDate);
        criteria.setRankId(rankId);

        Paged<MartyredResponse> list = martyredService.searchMartyred(
                page,
                size,
                criteria
        );

        model.addAttribute("objectList", list);
        model.addAttribute("srcUrl", martyredSearchPathBuilder(request));

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        return "admin/martyred";
    }

    @GetMapping("/martyred/create-page")
    public String getCreatePage(Model model) {
        if (!model.containsAttribute("martyredRequest")) {
            model.addAttribute("martyredRequest", new MartyredRequest());
        }
        model.addAttribute("rankList", rankService.getAllRank());
        model.addAttribute("warList", warService.getAllWar());
        model.addAttribute("rewardList", rewardService.getAllReward());
        model.addAttribute("rankRequest", new RankRequest());
        return "admin/createMartyredPage";
    }

    @GetMapping("/martyred/{id}")
    public String getById(
            @PathVariable(value = "id") Long martyredId,
            Model model
    ) {
        if (!model.containsAttribute("martyredResponse")) {
            MartyredResponse response = martyredService.getMartyredById(martyredId);

            model.addAttribute("martyredResponse", response);

            model.addAttribute("rankList", rankService.getAllRank());

            model.addAttribute("warList", warService.findWarByNotInIds(
                    response.getWars().stream().map(WarResponse::getId).collect(Collectors.toList())
            ));

            model.addAttribute("rewardList", rewardService.findRewardByNotInIds(
                    response.getRewards().stream().map(RewardResponse::getId).collect(Collectors.toList())
            ));
        }
        return "admin/updateMartyredPage";
    }

    @GetMapping(value = "/martyred/get/delete")
    public String getById4Delete(
            @RequestParam("id") Long id,
            Model model
    ) {
        model.addAttribute("martyredResponse", martyredService.getMartyredById(id));
        return "admin/martyredRequestForm";
    }

    @PostMapping("/martyred/create")
    public String createMartyred(
            @Validated @ModelAttribute("martyredRequest") final MartyredRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.martyredRequest", bindingResult);
            redirectAttributes.addFlashAttribute("martyredRequest", request);
            return "redirect:/martyred/create-page";
        }
        martyredService.createMartyred(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/martyred";
    }

    @PostMapping("/martyred/update")
    public String updateMartyred(
            @Validated @ModelAttribute("martyredRequest") final MartyredRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.martyredResponse", bindingResult);
            redirectAttributes.addFlashAttribute("martyredResponse", request);
            return "redirect:/martyred/" + request.getId();
        }
        martyredService.updateMartyred(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/martyred";
    }

    @PostMapping("/martyred/delete")
    public String deleteMartyred(
            @RequestParam("id") Long martyredId,
            final RedirectAttributes redirectAttributes
    ) {
        martyredService.deleteMartyred(martyredId);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/martyred";
    }

    //CLIENT

    @GetMapping("/cl/martyred")
    public String clientGetMartyred(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "9") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "birthDate", required = false) String birthDate,
            @RequestParam(value = "fatherName", required = false) String fatherName,
            @RequestParam(value = "rank", required = false) Long rankId,
            HttpServletRequest request,
            Model model
    ) {
        var criteria = new MartyredSearchCriteria();
        criteria.setName(name);
        criteria.setSurname(surname);
        criteria.setFatherName(fatherName);
        criteria.setBirthDate(birthDate);
        criteria.setRankId(rankId);

        Paged<MartyredResponse> list = martyredService.searchMartyred(
                page,
                size,
                criteria
        );


        model.addAttribute("objectList", list);
        model.addAttribute("srcUrl", martyredSearchPathBuilder(request));
        model.addAttribute("ranks", rankService.getAllRank());
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());

        return "client/martyred";
    }

    @GetMapping("/cl/martyred/{id}")
    public String clientGetMartyredById(
            @PathVariable(value = "id") Long id,
            Model model
    ) {
        model.addAttribute("martyred", martyredService.getMartyredById(id));
        model.addAttribute("martyredList", martyredService.get3MartyredByNotId(id));
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/oneMartyred";
    }
}

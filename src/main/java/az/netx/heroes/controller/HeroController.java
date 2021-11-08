package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.HeroSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.request.HeroRequest;
import az.netx.heroes.model.request.RankRequest;
import az.netx.heroes.model.response.HeroResponse;
import az.netx.heroes.service.HeroService;
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
@RequestMapping(value = "/hero")
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;

    @GetMapping
    public String getHeroPage(
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
        var criteria = new HeroSearchCriteria();
        criteria.setName(name);
        criteria.setSurname(surname);
        criteria.setFatherName(fatherName);
        criteria.setBirthDate(birthDate);
        criteria.setRankId(rankId);

        Paged<HeroResponse> list = heroService.searchHero(
                page,
                size,
                criteria
        );

        model.addAttribute("objectList", list);
        model.addAttribute("srcUrl", postSearchPathBuilder(request));

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        return "admin/hero";
    }

    @GetMapping("/create-page")
    public String getCreatePage(Model model) {
        if (!model.containsAttribute("heroRequest")) {
            model.addAttribute("heroRequest", new HeroRequest());
        }
        model.addAttribute("rankRequest", new RankRequest());
        return "admin/createHeroPage";
    }

    @GetMapping("/{id}")
    public String getById(
            @PathVariable(value = "id") Long heroId,
            Model model
    ) {
        if (!model.containsAttribute("heroResponse")) {
            HeroResponse response = heroService.getHeroById(heroId);
            model.addAttribute("heroResponse", response);
        }
        return "admin/updatePostPage";
    }

    @PostMapping("/create")
    public String createHero(
            @Validated @ModelAttribute("heroRequest") final HeroRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroRequest", bindingResult);
            redirectAttributes.addFlashAttribute("heroRequest", request);
            return "redirect:/hero/create-page";
        }
        heroService.createHero(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/hero";
    }

    @PostMapping("/update")
    public String updateHero(
            @Validated @ModelAttribute("heroRequest") final HeroRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroRequest", bindingResult);
            redirectAttributes.addFlashAttribute("heroRequest", request);
            return "redirect:/hero/" + request.getId();
        }
        heroService.updateHero(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/post";
    }

    @PostMapping("/delete")
    public String deleteHero(
            @RequestParam("id") Long heroId,
            final RedirectAttributes redirectAttributes
    ) {
        heroService.deleteHero(heroId);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/post";
    }
}
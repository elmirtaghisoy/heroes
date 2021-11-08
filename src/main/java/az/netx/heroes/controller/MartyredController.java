package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.MartyredSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.request.MartyredRequest;
import az.netx.heroes.model.request.RankRequest;
import az.netx.heroes.model.response.MartyredResponse;
import az.netx.heroes.service.MartyredService;
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
@RequestMapping(value = "/martyred")
@RequiredArgsConstructor
public class MartyredController {

    private final MartyredService martyredService;

    @GetMapping
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
        model.addAttribute("srcUrl", postSearchPathBuilder(request));

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        return "admin/martyred";
    }

    @GetMapping("/create-page")
    public String getCreatePage(Model model) {
        if (!model.containsAttribute("martyredRequest")) {
            model.addAttribute("martyredRequest", new MartyredRequest());
        }
        model.addAttribute("rankRequest", new RankRequest());
        return "admin/createMartyredPage";
    }

    @GetMapping("/{id}")
    public String getById(
            @PathVariable(value = "id") Long martyredId,
            Model model
    ) {
        if (!model.containsAttribute("heroResponse")) {
            MartyredResponse response = martyredService.getMartyredById(martyredId);
            model.addAttribute("heroResponse", response);
        }
        return "admin/updateMartyredPage";
    }

    @PostMapping("/create")
    public String createMartyred(
            @Validated @ModelAttribute("martyredRequest") final MartyredRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.martyredRequest", bindingResult);
            redirectAttributes.addFlashAttribute("martyredRequest", request);
            return "redirect:/hero/create-page";
        }
        martyredService.createMartyred(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/martyred";
    }

    @PostMapping("/update")
    public String updateMartyred(
            @Validated @ModelAttribute("martyredRequest") final MartyredRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.martyredRequest", bindingResult);
            redirectAttributes.addFlashAttribute("martyredRequest", request);
            return "redirect:/martyred/" + request.getId();
        }
        martyredService.updateMartyred(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/martyred";
    }

    @PostMapping("/delete")
    public String deleteMartyred(
            @RequestParam("id") Long martyredId,
            final RedirectAttributes redirectAttributes
    ) {
        martyredService.deleteMartyred(martyredId);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/martyred";
    }
}

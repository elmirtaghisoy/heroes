package az.netx.heroes.controller;

import az.netx.heroes.model.request.VictoryHistoryRequest;
import az.netx.heroes.service.PostCategoryService;
import az.netx.heroes.service.VictoryHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

import static az.netx.heroes.controller.ControllerConstraints.SUCCESS;

@Controller
@RequiredArgsConstructor
public class VictoryHistoryController {

    private final VictoryHistoryService victoryHistoryService;
    private final PostCategoryService postCategoryService;

    @GetMapping("/admin/hist/all")
    public String getAllHist(
            Model model
    ) {
        model.addAttribute("histList", victoryHistoryService.getAllHist());
        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }
        return "admin/hist";
    }

    @PostMapping("/admin/hist/update")
    public String updateHist(
            @Validated @ModelAttribute("histRequest") final VictoryHistoryRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.histResponse", bindingResult);
            redirectAttributes.addFlashAttribute("histResponse", request);
            return "redirect:/admin/hist/all";
        }
        victoryHistoryService.updateHist(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/hist/all";
    }

    @GetMapping("/history")
    public String getAllHistClient(
            Model model
    ) {
        model.addAttribute("histList", victoryHistoryService.getAllHist());
        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/hist";
    }

    @GetMapping("/history/{id}")
    public String getHistClientById(
            @PathVariable(value = "id") Long id,
            Model model
    ) {
        model.addAttribute("hist", victoryHistoryService.getHistById(id));
        model.addAttribute("histList", victoryHistoryService.get2HistByNotId(id));
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/histOne";
    }

}

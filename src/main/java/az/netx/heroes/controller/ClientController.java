package az.netx.heroes.controller;

import az.netx.heroes.service.PostCategoryService;
import az.netx.heroes.service.VictoryHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final VictoryHistoryService victoryHistoryService;
    private final PostCategoryService postCategoryService;

    @GetMapping("/")
    public String index(
            Model model
    ) {
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/index";
    }

    @GetMapping("/get/hist/{histId}")
    public String section(
            @PathVariable("histId") Long warId,
            Model model
    ) {
        model.addAttribute("hist", victoryHistoryService.getHistById(warId));
        return "client/layout/warHistorySection";
    }

    @GetMapping("/soldier")
    public String soldier() {
        return "client/team";
    }


    @GetMapping("/warHistory")
    public String warHistory() {
        return "client/warHistory";
    }

}

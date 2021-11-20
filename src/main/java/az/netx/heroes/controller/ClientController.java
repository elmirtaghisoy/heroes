package az.netx.heroes.controller;

import az.netx.heroes.service.WarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class ClientController {

    private final WarService warService;

    @GetMapping("/home")
    public String index() {
        return "client/index";
    }

    @GetMapping("/get/war/{warId}")
    public String section(
            @PathVariable("warId") Long warId,
            Model model
    ) {
        model.addAttribute("war", warService.getWarById(warId));
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

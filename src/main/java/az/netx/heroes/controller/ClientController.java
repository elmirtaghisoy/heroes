package az.netx.heroes.controller;

import az.netx.heroes.model.request.MessageRequest;
import az.netx.heroes.service.MessageService;
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

import static az.netx.heroes.controller.ControllerConstraints.SUCCESS;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final VictoryHistoryService victoryHistoryService;
    private final PostCategoryService postCategoryService;
    private final MessageService messageService;

    @GetMapping("/")
    public String index(
            Model model
    ) {
        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        if (!model.containsAttribute("messageRequest")) {
            model.addAttribute("messageRequest", new MessageRequest());
        }

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

    @PostMapping("/message/send")
    public String sendMessage(
            @Validated @ModelAttribute("messageRequest") final MessageRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageRequest", bindingResult);
            redirectAttributes.addFlashAttribute("messageRequest", request);
            return "redirect:/";
        }
        messageService.createMessage(request);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/";
    }

    @GetMapping("/contact")
    public String getContactPage(
            Model model
    ) {
        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        if (!model.containsAttribute("messageRequest")) {
            model.addAttribute("messageRequest", new MessageRequest());
        }

        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/contact";
    }

    @GetMapping("/partner")
    public String getPartnerPage(
            Model model
    ) {
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/partner";
    }

    @GetMapping("/team")
    public String getTeamPage(
            Model model
    ) {
        model.addAttribute("categoryList", postCategoryService.getAllPostCategory());
        return "client/team";
    }
}

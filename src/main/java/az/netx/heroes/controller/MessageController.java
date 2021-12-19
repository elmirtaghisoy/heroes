package az.netx.heroes.controller;

import az.netx.heroes.component.criteria.MessageSearchCriteria;
import az.netx.heroes.component.paging.Paged;
import az.netx.heroes.model.response.MessageResponse;
import az.netx.heroes.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

import static az.netx.heroes.controller.ControllerConstraints.SUCCESS;
import static az.netx.heroes.util.SearchUtil.messageSearchPathBuilder;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/admin/message")
    public String getMessagePageAdmin(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "15") int size,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "status", required = false, defaultValue = "ALL") String status,
            @RequestParam(value = "readDateFrom", required = false, defaultValue = "2000-01-01") String readDateFrom,
            @RequestParam(value = "receivedDateFrom", required = false, defaultValue = "2000-01-01") String receivedDateFrom,
            @RequestParam(value = "readDateTo", required = false, defaultValue = "2100-01-01") String readDateTo,
            @RequestParam(value = "receivedDateTo", required = false, defaultValue = "2100-01-01") String receivedDateTo,
            HttpServletRequest request,
            Model model
    ) {
        var criteria = new MessageSearchCriteria();
        criteria.setEmail(email);
        criteria.setStatus(status);
        criteria.setReadDateFrom(readDateFrom);
        criteria.setReceivedDateFrom(receivedDateFrom);
        criteria.setReadDateTo(readDateTo);
        criteria.setReceivedDateTo(receivedDateTo);

        Paged<MessageResponse> list = messageService.searchMessage(
                page,
                size,
                criteria
        );

        model.addAttribute("objectList", list);
        model.addAttribute("srcUrl", messageSearchPathBuilder(request));

        if (model.containsAttribute("success")) {
            model.addAttribute("success");
        }

        return "admin/message";
    }

    @GetMapping("/admin/message/read")
    public String getByIdAdmin(
            @RequestParam(value = "id") Long id,
            Model model
    ) {
        model.addAttribute("messageResponse", messageService.getMessageById(id));
        return "admin/viewMessagePage";
    }

    @PostMapping("/admin/message/delete")
    public String deleteByIdAdmin(
            @RequestParam(value = "id") Long id,
            final RedirectAttributes redirectAttributes
    ) {
        messageService.deleteMessage(id);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/message";
    }
}

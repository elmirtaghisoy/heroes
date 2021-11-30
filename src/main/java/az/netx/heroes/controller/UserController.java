package az.netx.heroes.controller;

import az.netx.heroes.component.mapper.ObjectMapper;
import az.netx.heroes.model.request.UserAddRequest;
import az.netx.heroes.model.request.UserRequest;
import az.netx.heroes.model.response.UserResponse;
import az.netx.heroes.service.UserService;
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
import java.security.Principal;

import static az.netx.heroes.controller.ControllerConstraints.INVALID_PASS;
import static az.netx.heroes.controller.ControllerConstraints.SUCCESS;
import static az.netx.heroes.controller.ControllerConstraints.USERNAME_ALREADY_EXIST;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping("/admin/user")
    public String getUserPage(
            Model model
    ) {
        model.addAttribute("users", userService.findAllUser());
        return "admin/user";
    }

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/admin/user/activate-page")
    public String getActivationPage(
            Principal principal,
            Model model,
            HttpServletRequest request
    ) {
        if (!model.containsAttribute("userResponse")) {
            UserResponse user = objectMapper.E2R(userService.getUserByUsername(principal.getName()));
            if (user.getStatus().equals("DEACTIVE")) {
                model.addAttribute("userResponse", user);
                return "admin/activationPage";
            }
        } else if (model.containsAttribute("userResponse")) {
            return "admin/activationPage";
        }

        if (model.containsAttribute("error")) {
            model.addAttribute("error");
        }

        request.getSession().setAttribute("LoggedUser", objectMapper.E2R(userService.getUserByUsername(principal.getName())));

        return "redirect:/admin/post";
    }

    @GetMapping("/admin/user/create-page")
    public String getCreatePage(Model model) {
        if (!model.containsAttribute("userAddRequest")) {
            model.addAttribute("userAddRequest", new UserAddRequest());
        }
        if (model.containsAttribute("error")) {
            model.addAttribute("error");
        }
        return "admin/createUserPage";
    }

    @GetMapping("/admin/user/{id}")
    public String getById(
            @PathVariable(value = "id") Long id,
            Model model
    ) {
        if (!model.containsAttribute("userAddRequest")) {
            model.addAttribute("userAddRequest", userService.getUserById(id));
        }
        if (model.containsAttribute("error")) {
            model.addAttribute("userAddRequest", userService.getUserById(id));
            model.addAttribute("error");
        }
        return "admin/updateUserPage";
    }


    @PostMapping("/admin/create/user")
    public String createUser(
            @Validated @ModelAttribute("userAddRequest") final UserAddRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddRequest", bindingResult);
            redirectAttributes.addFlashAttribute("userAddRequest", request);
            return "redirect:/admin/user/create-page";
        }
        String result = userService.saveUser(request);
        if ("USERNAME_ALREADY_EXIST".equals(result)) {
            redirectAttributes.addFlashAttribute("error", USERNAME_ALREADY_EXIST);
            return "redirect:/admin/user/create-page";
        }
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/update/user")
    public String updateUser(
            @Validated @ModelAttribute("userAddRequest") final UserAddRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddRequest", bindingResult);
            redirectAttributes.addFlashAttribute("userAddRequest", request);
            return "redirect:/admin/user/" + request.getId();
        }
        String result = userService.updateUser(request);
        if ("USERNAME_ALREADY_EXIST".equals(result)) {
            redirectAttributes.addFlashAttribute("error", USERNAME_ALREADY_EXIST);
            return "redirect:/admin/user/" + request.getId();
        }
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/activate")
    public String activateUser(
            @Validated @ModelAttribute("userRequest") final UserRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userResponse", bindingResult);
            redirectAttributes.addFlashAttribute("userResponse", request);
            return "redirect:/admin/user/activate-page";
        }
        String result = userService.activateUser(request);
        switch (result) {
            case "USERNAME_ALREADY_EXIST":
                redirectAttributes.addFlashAttribute("error", USERNAME_ALREADY_EXIST);
                return "redirect:/admin/user/activate-page";
            case "INVALID_PASS":
                redirectAttributes.addFlashAttribute("error", INVALID_PASS);
                return "redirect:/admin/user/activate-page";
            default:
                redirectAttributes.addFlashAttribute("success", SUCCESS);
                return "redirect:/admin/post";
        }
    }


    @PostMapping("/admin/user/{action}")
    public String userActivity(
            @RequestParam("id") Long id,
            @PathVariable("action") String action,
            final RedirectAttributes redirectAttributes
    ) {
        userService.userActivity(id, action);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/reset/user")
    public String resetUser(
            @RequestParam("id") Long id,
            final RedirectAttributes redirectAttributes
    ) {
        userService.resetUser(id);
        redirectAttributes.addFlashAttribute("success", SUCCESS);
        return "redirect:/admin/user";
    }

}
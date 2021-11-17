package az.netx.heroes.controller;

import az.netx.heroes.component.mapper.ObjectMapper;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Objects;

import static az.netx.heroes.controller.ControllerConstraints.INVALID_PASS;
import static az.netx.heroes.controller.ControllerConstraints.SUCCESS;
import static az.netx.heroes.controller.ControllerConstraints.USERNAME_ALREADY_EXIST;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/user")
    public String getUserPage(
            Model model
    ) {
        model.addAttribute("users", userService.findAllUser());
        return "admin/user";
    }

    @GetMapping("/user/activate-page")
    public String getActivationPage(
            Principal principal,
            Model model
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

        return "redirect:/post";
    }

    @PostMapping("/create/user")
    public String createUser(
            @RequestParam("pass") String pass,
            @RequestParam("username") String username
    ) {
        if (Objects.nonNull(pass) && Objects.nonNull(username)) {
            if (pass.isEmpty() || username.isEmpty()) {
                //error
            } else {
                //success
            }
        }
        return "admin/parameters";
    }

    @PostMapping("/user/activate")
    public String activateUser(
            @Validated @ModelAttribute("userRequest") final UserRequest request,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userResponse", bindingResult);
            redirectAttributes.addFlashAttribute("userResponse", request);
            return "redirect:/user/activate-page";
        }
        String result = userService.activateUser(request);
        switch (result) {
            case "USERNAME_ALREADY_EXIST":
                redirectAttributes.addFlashAttribute("error", USERNAME_ALREADY_EXIST);
                return "redirect:/user/activate-page";
            case "INVALID_PASS":
                redirectAttributes.addFlashAttribute("error", INVALID_PASS);
                return "redirect:/user/activate-page";
            default:
                redirectAttributes.addFlashAttribute("success", SUCCESS);
                return "redirect:/post";
        }
    }


    @PostMapping("/deactivate/user")
    public String deactivateUser(
            @RequestParam("pass") String pass,
            @RequestParam("username") String username
    ) {
        if (Objects.nonNull(pass) && Objects.nonNull(username)) {
            if (pass.isEmpty() || username.isEmpty()) {
                //error
            } else {
                //success
            }
        }
        return "admin/parameters";
    }

    @PostMapping("/activate/user")
    public String activateUser(
            @RequestParam("pass") String pass,
            @RequestParam("username") String username
    ) {
        if (Objects.nonNull(pass) && Objects.nonNull(username)) {
            if (pass.isEmpty() || username.isEmpty()) {
                //error
            } else {
                //success
            }
        }
        return "admin/parameters";
    }

    @PostMapping("/refresh/user")
    public String refreshPassword(
            @RequestParam("pass") String pass,
            @RequestParam("username") String username
    ) {
        if (Objects.nonNull(pass) && Objects.nonNull(username)) {
            if (pass.isEmpty() || username.isEmpty()) {
                //error
            } else {
                //success
            }
        }
        return "admin/parameters";
    }


}
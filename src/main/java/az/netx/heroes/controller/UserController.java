package az.netx.heroes.controller;

import az.netx.heroes.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping("/activate/app")
    public String getActivationPage() {
//        return "admin/activationPage";
        return "redirect:/post";
    }

    @PostMapping("/activate/app")
    public String activateApp(
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

    @PostMapping("/add/user")
    public String addUser(
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
    public String refreshUser(
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

    @PostMapping("/delete/user")
    public String deleteUser(
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
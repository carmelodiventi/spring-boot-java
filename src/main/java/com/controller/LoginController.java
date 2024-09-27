package com.controller;

import com.entity.User;
import com.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String login(User user, Model model) {
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/sign-in")
    public String signIn(User user, Model model, HttpSession session) {
        Optional<User> result = userService.signIn(user);
        if (result.isPresent()) {
            User loggedInUser = result.get();
            session.setAttribute("loggedInUser", loggedInUser);
            model.addAttribute("user", loggedInUser);
            model.addAttribute("products", "");
            return "redirect:/home";
        }
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}

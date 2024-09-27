package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String register(User user, Model model) {
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/sign-up")
    public String signUp(User user, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("user", user);
        String result = userService.signUp(user);
        if (result.equals("success")) {
            redirectAttributes.addFlashAttribute("success", "User registered successfully, you can login now");
            return "redirect:/";
        }
        model.addAttribute("error", result);
        return "register";
    }

}

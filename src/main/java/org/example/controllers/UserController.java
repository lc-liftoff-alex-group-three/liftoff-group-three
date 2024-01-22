package org.example.controllers;

import org.example.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
    @RequestMapping("user")
    public class UserController {
        @GetMapping("/register")
        public String showRegistrationForm(Model model) {
            model.addAttribute(new User());
            return "register.html";
        }

        @PostMapping
        public String registerNewUser(Model model, @ModelAttribute @Valid User user, Errors errors, String verify) {

            model.addAttribute("verify", verify);

            if (user.getPassword().equals(verify) && errors.hasErrors()) {
                return "dashboard.html";
            } else {
                model.addAttribute("error", "Passwords do not match");
                return "register.html";
            }
        }
}

package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/dragon-gallery")
public class RewardsController {

    @GetMapping
    public String showRewardGallery(Model model) {
        List<String> rewards = Arrays.asList("red.jpg", "green.jpg", "orangeandyellow.jpg", "brownandwhite.jpg", "blueandpurple.jpg", "rainbow.jpg");
        model.addAttribute("rewards", rewards);
        return "dragon-gallery"; // Assuming your HTML template is directly in the "templates" folder
    }

}
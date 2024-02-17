package org.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/reward-gallery")
public class RewardsController {

    @GetMapping("/reward-gallery")
    public String showRewardGallery(Model model) {
        List<String> rewards = Arrays.asList("red.jpg", "green.jpg", "orange&yellow.jpg", "brown&white.jpg", "blue&purple.jpg", "rainbow.jpg");
        model.addAttribute("rewards", rewards);
        return "rewards/reward-gallery"; // Assuming you have a rewards/gallery.html template
    }

}
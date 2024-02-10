package org.example.controllers;

import org.example.data.GoalRepository;
import org.example.models.Goals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GoalsController {

    @Autowired
    private GoalRepository;
    @GetMapping("/goal-form")
    public String showGoalForm(Model model) {
        model.addAttribute("goal", new Goals());
        return "goal-form";
    }

    @PostMapping("/submit-goal")
    public String submitGoalForm(@ModelAttribute Goals goal) {
        // Handle form submission here will eventually connect to SQL database
        return "redirect:/goal-form";
    }
}
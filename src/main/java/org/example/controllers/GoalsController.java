package org.example.controllers;

import org.example.data.GoalRepository;
import org.example.models.Goals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Controller
public class GoalsController {

    @Autowired
    private GoalRepository goalRepository;
    @GetMapping("/goal-form")
    public String showGoalForm(Model model) {
        List<String> dragonRewards = Arrays.asList("Red", "Orange & Yellow", "Green", "Blue & Purple", "Brown & White", "Rainbow");
        model.addAttribute("dragonRewards", dragonRewards);
        model.addAttribute("goal", new Goals());
        return "goal-form";
    }
    @PostMapping("/submit-goal")
    public String submitGoalForm(@ModelAttribute Goals goal, @RequestParam(value = "completed", required = false) boolean completed, @RequestParam(value = "approved", required = false) boolean approved) {
        goal.setCompleted(completed);
        if (completed) {
            goal.setCompletedDate(LocalDate.now());
        }
        goal.setApproved(approved);
        if (approved) {
            goal.setApprovedDate(LocalDate.now());
        }
        goalRepository.save(goal);
        return "redirect:/goals";
    }
    @GetMapping("/goals")
    public String listGoals(Model model) {
        List<Goals> goals = (List<Goals>) goalRepository.findAll();
        model.addAttribute("goals", goals);
        return "goals"; // Need to have a goals.html template to display the list of goals
    }
    @DeleteMapping("/delete-goal/{id}")
    public String deleteGoal(@PathVariable("id") int id) {
        goalRepository.deleteById((long) id);
        return "redirect:/goals";
    }

}
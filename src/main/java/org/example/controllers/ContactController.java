package org.example.controllers;


import org.example.models.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ContactController {

    @GetMapping("/contact-form")
    public String renderContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@Valid @ModelAttribute Contact contact, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "contact"; // If errors, return to the contact view.
        }
        model.addAttribute("contact", new Contact());
        redirectAttributes.addFlashAttribute("message", "Your message has been submitted.");
        return "redirect:/contact-form";
    }



}

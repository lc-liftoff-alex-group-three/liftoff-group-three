package org.example.controllers;

import org.example.data.ContactRepository;
import org.example.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @GetMapping("/contact-form")
    public String renderContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@Valid @ModelAttribute Contact contact, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("errors", errors);
            return "contact"; // If errors, return to the contact view.
        }
        contactRepository.save(contact);

        model.addAttribute("message", "Your message has been submitted.");
        return "contact"; // Stay on the same page to show the message
    }



}
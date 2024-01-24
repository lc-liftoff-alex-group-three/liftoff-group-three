package org.example.controllers;



@controller
public class ContactController {

    @GetMapping("/contact")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String comtactSubmit(@ModelAttribute Contact contact, Model model) {
        model.addAttribute("contact", contact);
        return "result";
    }



}

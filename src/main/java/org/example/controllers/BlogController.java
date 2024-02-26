package org.example.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

class BlogController {

    @GetMapping
    public String blog(Model model){
        model.addAttribute("title", "Dragon Admin Blog");
        return "blog";
    }

    @GetMapping("/new-post")
    public String newPostForm(){
        return "newPost";
    }

    @PostMapping("/ new-post")
    public String handlePostForm(Model model, @RequestParam String name, @RequestParam String status){
        model.addAttribute("name", name);
        model.addAttribute("message", message);
        return "displayPost";
    }
}

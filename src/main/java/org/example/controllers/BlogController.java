package org.example.controllers;


import org.springframework.web.bind.annotation.GetMapping;

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
}

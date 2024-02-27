package org.example.controllers;


import org.example.data.BlogData;
import org.example.models.Blog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

class BlogController {

    private BlogData blogData   = new BlogData();



    @GetMapping
    public String home(Model model){
        model.addAttribute("title", "Dragon Admin Blog");
        model.addAttribute("messages", BlogData.getAll());
        return "home";
    }

    @GetMapping("/new-post")
    public String newPostForm(){
        return "newPost";
    }

    @PostMapping("/ new-post")
    public String handlePostForm(Model model, @ModelAttribute Blog blog){
       blogData.add(blog);
        return "displayPost";
    }
}

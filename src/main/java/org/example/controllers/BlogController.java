package org.example.controllers;


import org.example.data.BlogRepository;
import org.example.models.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
class BlogController {

    private BlogRepository blogData   = new BlogRepository();

    @GetMapping
    public String home(Model model){
        model.addAttribute("title", "Dragon Admin Blog");
        model.addAttribute("blogs", BlogRepository.getAll());
        return "home";
    }

    @GetMapping("/new-post")
    public String newPostForm(Model model){
        model.addAttribute("blog", new Blog());
        return "newPost";
    }

    @PostMapping("/new-post")
    public String handlePostForm(@ModelAttribute Blog blog, Model model) {
        blogData.add(blog);
        model.addAttribute("blog", blog);
        return "displayPost";
    }

}

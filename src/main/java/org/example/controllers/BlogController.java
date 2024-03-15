package org.example.controllers;


import org.example.models.Blog;
import org.example.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BlogController {

   @Autowired
   private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public String showBlogs(Model model) {
        List<Blog> blogs = blogService.getBlogsSortedByTime();
        model.addAttribute("blogs", blogs);
        return "home";

    }


    @GetMapping("/new-post")
    public String newPostForm(Model model){
        model.addAttribute("blog", new Blog());
        return "newPost";
    }

    @PostMapping("/new-post")
    public String handlePostForm(@ModelAttribute Blog blog, Model model) {
        blogService.addBlog(blog);
        model.addAttribute("blog", blog);
        return "displayPost";
    }

}

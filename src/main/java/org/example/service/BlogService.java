package org.example.service;

import org.example.data.BlogRepository;
import org.example.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getBlogsSortedByTime() {
        Sort.Order sort;
        return blogRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
    }

    public void addBlog(Blog blog) {
    }
}



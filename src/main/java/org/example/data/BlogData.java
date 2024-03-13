package org.example.data;

import org.example.models.Blog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BlogData {

    private static Map<Integer, Blog> blogs = new HashMap<>();

    public static Collection<Blog> getAll() {
        return blogs.values();     }
    public static void add(Blog blog) {blogs.put(blog.getId(), blog);}
    public static Blog getById (Integer id) { return blogs.get(id);}
    public static void remove (Integer id) {
        if (blogs
                .containsKey(id)) {
            blogs.remove(id);
        }
    }



}

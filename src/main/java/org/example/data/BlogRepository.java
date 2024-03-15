package org.example.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.models.Blog;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findAll(Sort sort);
}

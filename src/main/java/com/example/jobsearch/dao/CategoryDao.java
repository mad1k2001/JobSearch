package com.example.jobsearch.dao;

import com.example.jobsearch.model.Category;
import com.example.jobsearch.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryDao {
    private final JdbcTemplate template;

    public List<Category> getCategories() {
        String sql = """
        SELECT * from CATEGORIES
        """;
        return template.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}

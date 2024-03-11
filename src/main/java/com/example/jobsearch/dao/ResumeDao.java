package com.example.jobsearch.dao;

import com.example.jobsearch.model.Resume;
import com.example.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate template;
    public List<Resume> getResume(){
        String sql = """
                select * from resumes
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

}

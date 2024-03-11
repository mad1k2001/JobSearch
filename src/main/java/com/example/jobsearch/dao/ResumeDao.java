package com.example.jobsearch.dao;

import com.example.jobsearch.model.Resume;
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

    public List<Resume> getResumeByCategory(Long categoryId) {
        String sql = """
                select * from resumes WHERE categoryId = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }

    public List<Resume> getResumeByApplicantId(Long applicantId) {
        String sql = """
                select * from resumes WHERE applicantId = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), applicantId);
    }

}

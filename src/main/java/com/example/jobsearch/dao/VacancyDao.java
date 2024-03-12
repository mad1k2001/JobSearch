package com.example.jobsearch.dao;

import com.example.jobsearch.model.User;
import com.example.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> getVacancies() {
        String sql = """
                SELECT * FROM vacancies
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByCategory(Long categoryId) {
        String sql = """
                SELECT * FROM vacancies WHERE categoryId = ?
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public List<Vacancy> getVacanciesForUser(Long userId) {
        String sql = """       
                SELECT v.* FROM vacancies v
                JOIN respondedApplications ra ON v.id = ra.vacancyId
                JOIN resumes r ON ra.resumeId = r.id
                WHERE r.applicantId = ?
            """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), userId);
    }
}

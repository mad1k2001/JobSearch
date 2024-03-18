package com.example.jobsearch.dao;

import com.example.jobsearch.model.RespondedApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RespondedApplicationDao {
    private final JdbcTemplate template;
    public List<RespondedApplication> getRespondedVacanciesForApplicant(Long applicantId) {
        String sql = """
                SELECT ra.*, v.* FROM respondedApplications ra
                INNER JOIN resumes r ON ra.resumeId = r.id
                INNER JOIN vacancies v ON ra.vacancyId = v.id
                WHERE r.applicantId = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(RespondedApplication.class), applicantId);
    }

    public List<RespondedApplication> getApplicationsByVacancy(Long vacancyId) {
        String sql = """
                SELECT ra.*, u.* FROM respondedApplications ra
                INNER JOIN resumes r ON ra.resumeId = r.id
                INNER JOIN users u ON r.applicantId = u.id
                WHERE ra.vacancyId = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(RespondedApplication.class), vacancyId);
    }
}

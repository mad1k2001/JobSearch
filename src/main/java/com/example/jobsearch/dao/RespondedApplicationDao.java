package com.example.jobsearch.dao;

import com.example.jobsearch.model.RespondedApplication;
import com.example.jobsearch.model.Resume;
import com.example.jobsearch.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

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

    public void addRespond(RespondedApplication respondedApplication) {
        String sql = """
                INSERT INTO respondedApplications (resumeid, vacancyid, confirmation)
                VALUES (?,?,?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, respondedApplication.getResumeId());
            ps.setLong(2, respondedApplication.getVacancyId());
            ps.setBoolean(3, respondedApplication.getConfirmation());
            return ps;
        }, keyHolder);

        Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void editRespond(RespondedApplication respondedApplication){
        String sql = """
                update respondedApplications
                set 
                    resumeId = :resumeId,
                    vacancyid = :vacancyId,
                    confirmation = :confirmation
                where id = :id;
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", respondedApplication.getResumeId())
                .addValue("vacancyId", respondedApplication.getVacancyId())
                .addValue("confirmation", respondedApplication.getConfirmation()));
    }
}

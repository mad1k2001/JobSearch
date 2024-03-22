package com.example.jobsearch.dao;

import com.example.jobsearch.model.Resume;
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
import java.util.Optional;

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

    public Optional<Resume> getResumeById(Long id) {
        String sql = """
                SELECT * FROM resumes WHERE id = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)
                .stream()
                .findFirst();
    }

    public List<Resume> getActiveResumes() {
        String sql = """
                SELECT * FROM resumes WHERE isActive = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public void setActive(Long resumeId, boolean isActive) {
        String sql = """
                update resumes
                set isActive = :isActive
                where id = :resumeId;
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("isActive", isActive)
                .addValue("resumeId", resumeId));
    }

    public List<Resume> getActiveResumesByCategory(Long categoryId) {
        String sql = """
                select * from resumes
                where resumes.categoryId = ? and isActive = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }

    public Long addResume(Resume resume) {
        String sql = """
                INSERT INTO resumes (applicantId, name, categoryId, salary, isActive, createdDate)
                VALUES (?,?,?,?,?,?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, resume.getApplicantId());
            ps.setString(2, resume.getName());
            ps.setLong(3, resume.getCategoryId());
            ps.setDouble(4, resume.getSalary());
            ps.setBoolean(5, resume.getIsActive());
            ps.setTimestamp(6, Timestamp.valueOf(resume.getCreatedDate()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void editResume(Resume resume) {
        String sql = """
                UPDATE resumes
                SET applicantId = :applicantid,
                    name = :name,
                    categoryId = :categoryId,
                    salary = :salary,
                    isActive = :isActive,
                    updateTime = :updateTime
                WHERE id = :id
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("applicantId", resume.getApplicantId())
                .addValue("name", resume.getName())
                .addValue("categoryId", resume.getCategoryId())
                .addValue("salary", resume.getSalary())
                .addValue("isActive", resume.getIsActive())
                .addValue("updateTime", resume.getUpdateTime())
                .addValue("id", resume.getId()));
    }

    public void deleteResume(Long id) {
        String sql = """
                DELETE FROM resumes WHERE id = ?
                """;
        template.update(sql, id);
    }

    public boolean isResumeDeletable(Long resumeId) {
        String sql = """
                SELECT COUNT(*) FROM respondedApplications WHERE resumeId = ?
                """;
        return template.query(sql, (rs, rowNum) -> true, resumeId).isEmpty();
    }
}

package com.example.jobsearch.dao;

import com.example.jobsearch.model.Resume;
import com.example.jobsearch.model.User;
import com.example.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate template;

    public List<Vacancy> getVacancies() {
        String sql = """
                SELECT * FROM vacancies
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByCategory(Long categoryId) {
        String sql = """
                SELECT * FROM vacancies WHERE categoryId = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public Optional<Vacancy>  getVacancyById(Long id){
        String sql = """
                SELECT * FROM vacancies WHERE id = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), id)
                .stream()
                .findFirst();
    }

    public List<Vacancy> getActiveVacancies(){
        String sql = """
                select * from vacancies
                where isActivate = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public void setActive(Long vacancyId, boolean isActivate) {
        String sql = """
                update vacancies
                set isActivate = :isActivate
                where id = :resumeId;
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("isActivate", isActivate)
                .addValue("vacancyId", vacancyId));
    }

    public List<Vacancy> getActiveVacanciesByCategory(Integer categoryId) {
        String sql = """
                select * from vacancies v
                where v.categoryId = ? and v.isActivate = true;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public Long addVacancy(Vacancy vacancy) {
        String sql = """
                INSERT INTO vacancies (name, description, categoryId, salary, isActivate, createdDate)
                VALUES (?,?,?,?,?,?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, vacancy.getName());
            ps.setString(2, vacancy.getDescription());
            ps.setLong(3, vacancy.getCategoryId());
            ps.setDouble(4, vacancy.getSalary());
            ps.setBoolean(5, vacancy.getIsActivate());
            ps.setTimestamp(6, Timestamp.valueOf(vacancy.getCreatedDate()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void editVacancy(Vacancy vacancy) {
        String sql = """
                UPDATE vacancies
                SET name = :name,
                    description = :description,
                    categoryId = :categoryId,
                    salary = :salary,
                    expFrom = :expFrom,
                    expTo = :expTo,
                    isActivate = :isActivate,
                    authorId = :authorId,
                    updateTime = :updateTime
                WHERE id = :id
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("name", vacancy.getName())
                .addValue("description", vacancy.getDescription())
                .addValue("categoryId", vacancy.getCategoryId())
                .addValue("salary", vacancy.getSalary())
                .addValue("isActivate", vacancy.getIsActivate())
                .addValue("updateTime", LocalDateTime.now())
                .addValue("id", vacancy.getId()));
    }

    public void deleteVacancy(Long id) {
        String sql = """
                DELETE FROM vacancies WHERE id = ?
                """;
        template.update(sql, id);
    }

    public boolean isVacancyDeletable(Long vacancyId) {
        String sql = """
                SELECT COUNT(*) FROM respondedApplications WHERE vacancyId = ?
                """;
        return template.query(sql, (rs, rowNum) -> true, vacancyId).isEmpty();
    }
}

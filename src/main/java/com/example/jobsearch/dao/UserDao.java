package com.example.jobsearch.dao;

import com.example.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate template;
    public List<User> getUsers() {
        String sql = """
                select * from users;
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> getUserByEmail(String email) {
        String sql = """
                select * from users where email = ?
                """;
        return Optional.ofNullable(template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email));
    }

    public List<User> getApplicantsForVacancy(Long vacancyId) {
        String sql = """
            select u.* from users u
            join resumes r ON u.id = r.applicantId
            join respondedApplications ra ON r.id = ra.resumeId
            where ra.vacancyId = ?
            """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), vacancyId);
    }

    public List<User> getUsersByParams(String name, String phone) {
        String sql = """
            select *
            from users
            where name ilike ? or PHONENUMBER ilike ?;
            """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), name, phone);
    }

    public boolean userExistsByEmail(String email) {
        String sql = """
            select COUNT(*) from users where email = ?
        """;
        Integer count = template.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

}

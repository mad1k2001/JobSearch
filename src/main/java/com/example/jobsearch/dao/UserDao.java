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

    public List<User> getUsersByName(String name) {
        String sql = """
        SELECT *
        FROM users
        WHERE name ILIKE ?
        """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), name);
    }

    public Optional<User> getUsersByPhoneNumber(String phoneNumber) {
        String sql = """
        SELECT *
        FROM users
        WHERE phoneNumber ILIKE ?
        """;
        return Optional.ofNullable(template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber));
    }

    public boolean userExistsByEmail(String email) {
        String sql = "select 1 from users where email = ?";
        return template.query(sql, (rs, rowNum) -> true, email).stream().findFirst().orElse(false);
    }
}

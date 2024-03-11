package com.example.jobsearch.dao;

import com.example.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<User> getUsersByName(String name) {
        String sql = """
                select * from users WHERE name = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), name);
    }

    public User getUsersByPhoneNumber(String phoneNumber) {
        String sql = """
                select * from users WHERE phoneNumber = ?
                """;
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber);
    }

    public User getUsersByEmail(String email) {
        String sql = """
                select * from users WHERE email = ?
                """;
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
    }
}

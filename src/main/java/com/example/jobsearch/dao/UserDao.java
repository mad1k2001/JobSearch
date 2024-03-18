package com.example.jobsearch.dao;

import com.example.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

    public void editUser (User user) {
        String sql = """
                UPDATE USERS
                SET NAME = : name,
                SURNAME = : surname,
                AGE = :age,
                email = :email,
                password = :password,
                phoneNumber = :phoneNumber,
                avatar = :avatar,
                accountType = :accountType
            WHERE id = :id;
            """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("age", user .getAge())
                .addValue("email", user.getEmail())
                .addValue ("password", user.getPassword())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue ("avatar", user.getAvatar())
                .addValue ("accountType", user.getAccountType())
                .addValue ("id",user.getId())
        );
    }
}

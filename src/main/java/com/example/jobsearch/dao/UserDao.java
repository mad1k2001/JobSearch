package com.example.jobsearch.dao;

import com.example.jobsearch.enums.AccountType;
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
import java.util.List;
import java.util.Objects;
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

    public Optional<User> getUserById(Long id) {
        String sql = """
                SELECT * FROM users WHERE id = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                .stream()
                .findFirst();
    }

    public boolean userExistsByEmail(String email) {
        return getUserByEmail(email).isPresent();
    }

    public Long addUser(User user){
        String sql = """
                INSERT INTO users (name, surname, age, email, password, phoneNumber, avatar, accountType)
                VALUES (?,?,?,?,?,?,?,?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(5, user.getAvatar());
            ps.setString(6, user.getAccountType().toString());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void editUser (User user) {
        String sql = """
                    UPDATE USERS
                    SET NAME = : name,
                    SURNAME = : surname,
                    AGE = :age,
                    password = :password,
                    phoneNumber = :phoneNumber,
                    avatar = :avatar
                WHERE id = :id;
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("surname", user.getSurname())
                .addValue("age", user .getAge())
                .addValue ("password", user.getPassword())
                .addValue("phoneNumber", user.getPhoneNumber())
                .addValue ("avatar", user.getAvatar())
                .addValue ("id",user.getId())
        );
    }

    public void save(User user) {
        String sql = """
                UPDATE USERS
                SET AVATAR = :avatar
                WHERE ID = :id;
                """;
        template.update(sql, new MapSqlParameterSource().addValue("id", user.getId()).addValue("avatar", user.getAvatar()));
    }

    public AccountType getUserAccountTypeById(Long userId) {
        String sql = """
                SELECT accountType FROM users WHERE id = ?
                """;
        return template.queryForObject(sql, AccountType.class, userId);
    }

}

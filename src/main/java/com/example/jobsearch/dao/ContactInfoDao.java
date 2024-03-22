package com.example.jobsearch.dao;

import com.example.jobsearch.model.ContactInfo;
import com.example.jobsearch.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ContactInfoDao {
    private final JdbcTemplate template;

    public List<ContactInfo> getContactInfoByResumeId(Long resumeId) {
        String sql = """
                SELECT * FROM contactInfos WHERE resumeId = ?
                """;
        return template.query(sql, new Object[]{resumeId}, new BeanPropertyRowMapper<>(ContactInfo.class));
    }

    public void addContact(ContactInfo contactInfo) {
        String sql = """
                INSERT INTO CONTACTINFOS (TYPEID, RESUMEID, CONTACTVALUE)
                VALUES (?,?,?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, contactInfo.getTypeId());
            ps.setLong(2, contactInfo.getResumeId());
            ps.setString(3, contactInfo.getContactValue());
            return ps;
        }, keyHolder);

        Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void editContact(ContactInfo contactInfo){
        String sql = """
                update contactInfos
                set 
                    typeId = :typeId,
                    resumeId = :resumeId,
                    contactValue = :contactValue
                where id = :id;
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("id", contactInfo.getId())
                .addValue("typeId", contactInfo.getTypeId())
                .addValue("resumeId", contactInfo.getResumeId())
                .addValue("contactValue", contactInfo.getContactValue()));
    }

    public void deleteContact(Long resumeId) {
        String sql = """
                delete from contactInfos
                where resumeId = ?;
                """;
        template.update(sql, resumeId);
    }
}

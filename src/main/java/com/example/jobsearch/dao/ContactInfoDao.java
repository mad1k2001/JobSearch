package com.example.jobsearch.dao;

import com.example.jobsearch.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void create(ContactInfo contactInfo) {
        String sql = """
                INSERT INTO contactInfos (typeId, resumeId, contactValue)
                 VALUES (:typeId, :resumeId, :contactValue)
                 """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", contactInfo.getId());
        params.addValue("typeId", contactInfo.getTypeId());
        params.addValue("resumeId", contactInfo.getResumeId());
        params.addValue("contactValue", contactInfo.getContactValue());

        template.update(sql, params);
    }

    public void update(ContactInfo contactInfo){
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

    public void delete(Long resumeId) {
        String sql = """
                delete from contactInfos
                where resumeId = ?;
                """;
        template.update(sql, resumeId);
    }
}

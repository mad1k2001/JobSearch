package com.example.jobsearch.dao;

import com.example.jobsearch.model.RespondedApplication;
import com.example.jobsearch.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {
    private final JdbcTemplate template;

    public List<WorkExperienceInfo> getWorkExperienceInfoByResumeId(Long resumeId) {
        String sql = """
                SELECT * FROM workExperienceInfo WHERE resumeId = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfo.class), resumeId);
    }

    public void addWorkExp(WorkExperienceInfo workExperienceInfo) {
        String sql = """
                insert into workExperienceInfo (resumeId, years, companyName, position, responsibility)
                values (?,?,?,?,?);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, workExperienceInfo.getResumeId());
            ps.setInt(2, workExperienceInfo.getYears());
            ps.setString(3, workExperienceInfo.getCompanyName());
            ps.setString(4, workExperienceInfo.getPosition());
            ps.setString(5, workExperienceInfo.getResponsibility());
            return ps;
        }, keyHolder);

        Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void editWorkExp(WorkExperienceInfo workExperienceInfo){
        String sql = """
                update workExperienceInfo
                set years = :years,
                    companyName = :companyName,
                    position = :position,
                    responsibility = :responsibility
                where id = :id;
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("years", workExperienceInfo.getYears())
                .addValue("companyName", workExperienceInfo.getCompanyName())
                .addValue("position", workExperienceInfo.getPosition())
                .addValue("responsibilities", workExperienceInfo.getResponsibility())
                .addValue("id", workExperienceInfo.getId()));
    }

    public void deleteWorkExp(Long resumeId) {
        String sql = """
                delete from workExperienceInfo
                where resumeId = ?;
                """;
        template.update(sql, resumeId);
    }
}

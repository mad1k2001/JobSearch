package com.example.jobsearch.dao;

import com.example.jobsearch.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public void create(WorkExperienceInfo workExperienceInfo) {
        String sql = """
                insert into workExperienceInfo (resumeId, years, companyName, position, responsibility)
                values (:resumeId, :years, :companyName, :position, :responsibility);
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", workExperienceInfo.getResumeId())
                .addValue("years", workExperienceInfo.getYears())
                .addValue("companyName", workExperienceInfo.getCompanyName())
                .addValue("position", workExperienceInfo.getPosition())
                .addValue("responsibility", workExperienceInfo.getResponsibility()));
    }

    public void update(WorkExperienceInfo workExperienceInfo){
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

    public void delete(Long resumeId) {
        String sql = """
                delete from workExperienceInfo
                where resumeId = ?;
                """;
        template.update(sql, resumeId);
    }
}

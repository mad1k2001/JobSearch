package com.example.jobsearch.dao;

import com.example.jobsearch.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {
    private final JdbcTemplate template;

    public List<EducationInfo> getEducationInfoByResumeId(Long resumeId) {
        String sql = """
                SELECT * FROM educationInfos WHERE resumeId = ?
                """;
        return template.query(sql, new BeanPropertyRowMapper<>(EducationInfo.class), resumeId);
    }

    public void create(EducationInfo educationInfo) {
        String sql = """
                insert into educationInfos (resumeId, institution, program, startDate, endDate, degree)
                values (:resumeId, :institution, :program, :startDate, :endDate, :degree);
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("resumeId", educationInfo.getResumeId())
                .addValue("institution", educationInfo.getInstitution())
                .addValue("program", educationInfo.getProgram())
                .addValue("startDate", educationInfo.getStartDate())
                .addValue("endDate", educationInfo.getEndDate())
                .addValue("degree", educationInfo.getDegree()));
    }

    public void update(EducationInfo educationInfo){
        String sql = """
                update educationInfos
                set 
                    institution = :institution,
                    program = :program,
                    startDate = :startDate,
                    endDate = :endDate,
                    degree = :degree
                where id = :id;
                """;
        template.update(sql, new MapSqlParameterSource()
                .addValue("id", educationInfo.getId())
                .addValue("institution", educationInfo.getInstitution())
                .addValue("program", educationInfo.getProgram())
                .addValue("startDate", educationInfo.getStartDate())
                .addValue("endDate", educationInfo.getEndDate())
                .addValue("degree", educationInfo.getDegree()));
    }

    public void delete(Long resumeId) {
        String sql = """
                delete from educationInfos
                where resumeId = ?;
                """;
        template.update(sql, resumeId);
    }
}

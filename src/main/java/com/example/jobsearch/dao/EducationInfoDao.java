package com.example.jobsearch.dao;

import com.example.jobsearch.model.EducationInfo;
import com.example.jobsearch.model.RespondedApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public void addEducation(EducationInfo educationInfo) {
        String sql = """
                INSERT INTO EDUCATIONINFOS (RESUMEID, INSTITUTION, PROGRAM, STARTDATE, ENDDATE, DEGREE)
                VALUES (?,?,?,?,?,?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, educationInfo.getResumeId());
            ps.setString(2, educationInfo.getInstitution());
            ps.setString(3, educationInfo.getProgram());
            ps.setDate(4, Date.valueOf(educationInfo.getStartDate()));
            ps.setDate(5, Date.valueOf(educationInfo.getEndDate()));
            ps.setString(6, educationInfo.getDegree());
            return ps;
        }, keyHolder);

        Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void editEducation(EducationInfo educationInfo){
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

    public void deleteEducation(Long resumeId) {
        String sql = """
                delete from educationInfos
                where resumeId = ?;
                """;
        template.update(sql, resumeId);
    }
}

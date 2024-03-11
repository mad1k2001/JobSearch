package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.ResumeDao;
import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.model.Resume;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    @Override
    public List<ResumeDto> getResume(){
        List<Resume> resumes = resumeDao.getResume();
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDo(e)));
        return dtos;
    }

    @Override
    public List<ResumeDto> getResumeByCategory(Long categoryId){
        List<Resume> resumes = resumeDao.getResumeByCategory(categoryId);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDo(e)));
        return dtos;
    }

    @Override
    public List<ResumeDto> getResumeByApplicantId(Long applicantId){
        List<Resume> resumes = resumeDao.getResumeByApplicantId(applicantId);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDo(e)));
        return dtos;
    }

    private ResumeDto mapToDo(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .applicantId(resume.getApplicantId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .build();
    }
}
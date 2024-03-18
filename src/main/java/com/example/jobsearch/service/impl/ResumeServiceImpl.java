package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.ResumeDao;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.model.Resume;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    @Override
    public List<ResumeDto> getResume(){
        List<Resume> resumes = resumeDao.getResume();
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDto(e)));
        return dtos;
    }

    @Override
    public List<ResumeDto> getResumeByCategory(Long categoryId){
        List<Resume> resumes = resumeDao.getResumeByCategory(categoryId);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDto(e)));
        return dtos;
    }

    @Override
    public List<ResumeDto> getResumeByApplicantId(Long applicantId){
        List<Resume> resumes = resumeDao.getResumeByApplicantId(applicantId);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDto(e)));
        return dtos;
    }

    @Override
    public Optional<ResumeDto> getResumeById(Long id) {
        return resumeDao.getResumeById(id)
                .map(this::mapToDto);
    }

    @Override
    public void addResume(ResumeDto resumeDto){
        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setApplicantId(resumeDto.getApplicantId());
        resume.setName(resumeDto.getName());
        resume.setCategoryId(resumeDto.getCategoryId());
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        resume.setCreatedDate(resumeDto.getCreatedDate());
        resume.setUpdateTime(resumeDto.getUpdateTime());
        resumeDao.addResume(resume);
    }

    @Override
    public void editResume(ResumeDto resumeDto) {
        Resume resume = new Resume();
        resume.setId(resumeDto.getId());
        resume.setApplicantId(resumeDto.getApplicantId());
        resume.setName(resumeDto.getName());
        resume.setCategoryId(resumeDto.getCategoryId());
        resume.setSalary(resumeDto.getSalary());
        resume.setIsActive(resumeDto.getIsActive());
        resume.setCreatedDate(resumeDto.getCreatedDate());
        resume.setUpdateTime(resumeDto.getUpdateTime());
        resumeDao.editResume(resume);
    }

    @Override
    public void deleteResume(Long id) {
        resumeDao.deleteResume(id);
    }

    private ResumeDto mapToDto(Resume resume) {
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
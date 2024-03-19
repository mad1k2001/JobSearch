package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.ResumeDao;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.enums.AccountType;
import com.example.jobsearch.model.Resume;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private User user;
    private final ResumeDao resumeDao;
    @Override
    public List<ResumeDto> getResume(){
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can view all resumes.");
        }
        List<Resume> resumes = resumeDao.getResume();
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDto(e)));
        return dtos;
    }

    @Override
    public List<ResumeDto> getResumeByCategory(Long categoryId){
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can view resumes by category.");
        }
        List<Resume> resumes = resumeDao.getResumeByCategory(categoryId);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDto(e)));
        return dtos;
    }

    @Override
    public List<ResumeDto> getResumeByApplicantId(Long applicantId){
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can view resumes by applicant.");
        }
        List<Resume> resumes = resumeDao.getResumeByApplicantId(applicantId);
        List<ResumeDto> dtos = new ArrayList<>();
        resumes.forEach(e -> dtos.add(mapToDto(e)));
        return dtos;
    }

    @Override
    public Optional<ResumeDto> getResumeById(Long id) {
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can view resumes by id.");
        }
        return resumeDao.getResumeById(id)
                .map(this::mapToDto);
    }

    @Override
    public void addResume(ResumeDto resumeDto){
        if (user.getAccountType() != AccountType.APPLICANT) {
           log.error("Only applicants can add resumes.");
        }
        Resume resume = makeResume(resumeDto);
        resumeDao.addResume(resume);
    }

    @Override
    public void editResume(ResumeDto resumeDto) {
        if (user.getAccountType() != AccountType.APPLICANT) {
            log.error("Only applicants can edit resumes.");
        }
        Resume resume = makeResume(resumeDto);
        resumeDao.editResume(resume);
    }

    @Override
    public void deleteResume(Long id) {
        if (user.getAccountType() != AccountType.APPLICANT) {
            log.error("Only applicants can delete resumes.");
        }resumeDao.deleteResume(id);
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

    private Resume makeResume(ResumeDto resumeDto){
        return Resume.builder()
                .id(resumeDto.getId())
                .applicantId(resumeDto.getApplicantId())
                .name(resumeDto.getName())
                .categoryId(resumeDto.getCategoryId())
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .createdDate(resumeDto.getCreatedDate())
                .updateTime(resumeDto.getUpdateTime())
                .build();
    }
}
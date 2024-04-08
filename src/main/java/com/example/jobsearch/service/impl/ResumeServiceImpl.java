package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.*;
import com.example.jobsearch.dto.*;
import com.example.jobsearch.model.*;
import com.example.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final UserDao userDao;
    private final WorkExperienceInfoDao workExperienceInfoDao;
    private final EducationInfoDao educationInfoDao;
    private final ContactInfoDao contactInfoDao;
    private final CategoryDao categoryDao;

    @Override
    public List<ResumeDto> getResume(){
        List<Resume> resumes = resumeDao.getResume();
        return getResumeDto(resumes);
    }

    @Override
    public List<ResumeDto> getResumeByCategory(Long categoryId){
        List<Resume> foundResumes = resumeDao.getResumeByCategory(categoryId);
        return getResumeDto(foundResumes);
    }

    @Override
    public List<ResumeDto> getResumeByApplicantId(Long applicantId){
        List<Resume> foundResumes = resumeDao.getResumeByApplicantId(applicantId);
        return getResumeDto(foundResumes);
    }

    @Override
    public Optional<ResumeDto> getResumeById(Long id) throws NoSuchElementException {
        return resumeDao.getResumeById(id)
                .map(this::mapToDto)
                .map(Optional::of)
                .orElseThrow(() -> new NoSuchElementException("Can't find resume with id: " + id));
    }

    @Override
    public Long addResume(ResumeDto resumeDto, Long applicantId) {
        Optional<User> userOptional = userDao.getUserById(applicantId);
        if (userOptional.isEmpty()) {
            log.error("Can't find user with id: " + applicantId);
            return null;
        }

        User user = userOptional.get();

        Resume resume = makeResume(resumeDto);

        if (resumeDto.getWorkExperienceList() != null) {
            for (WorkExperienceInfoDto workExperienceDto : resumeDto.getWorkExperienceList()) {
                if (workExperienceDto.getYears() > user.getAge()) {
                    log.error("Work experience years exceed user's age!");
                    return null;
                }
                workExperienceInfoDao.addWorkExp(makeWorkExperienceInfo(workExperienceDto));
            }
        }

        if (resumeDto.getEducationList() != null) {
            for (EducationInfoDto educationDto : resumeDto.getEducationList()) {
                long years = ChronoUnit.YEARS.between(educationDto.getStartDate(), educationDto.getEndDate());
                if (educationDto.getStartDate().isAfter(educationDto.getEndDate()) || years > user.getAge()) {
                    log.error("Incorrect date for education");
                    return null;
                }
                educationInfoDao.addEducation(makeEducationInfo(educationDto));
            }
        }

        if (resumeDto.getContactInfo() != null) {
            for (ContactInfoDto contactInfoDto : resumeDto.getContactInfo()) {
                contactInfoDao.addContact(makeContactInfo(contactInfoDto));
            }
        }
        return resumeDao.addResume(resume);
    }

    @Override
    public void editResume(ResumeDto resumeDto, Long applicantId, Long resumeId) {
        Optional<User> userOptional = userDao.getUserById(applicantId);
        if (userOptional.isEmpty()) {
            log.error("Can't find user whit id: " + applicantId);
            return;
        }
        User user = userOptional.get();

        Optional<Resume> resumeOptional = resumeDao.getResumeById(resumeId);
        if (resumeOptional.isEmpty()) {
            log.error("There's no resume by id " + resumeId);
            return;
        }

        Resume resume = makeResume(resumeDto);
        resumeDao.editResume(resume);

        updateContactInfo(resumeDto.getContactInfo(), resumeId);
        updateEducationInfo(resumeDto.getEducationList(), user, resumeId);
        updateWorkExperienceInfo(resumeDto.getWorkExperienceList(), user, resumeId);
    }

    private void updateContactInfo(List<ContactInfoDto> newContactInfoList, Long resumeId) {
        if (newContactInfoList == null) return;

        List<ContactInfo> existingContactInfoList = contactInfoDao.getContactInfoByResumeId(resumeId);

        for (ContactInfoDto newContactInfo : newContactInfoList) {
            existingContactInfoList.stream()
                    .filter(existingContactInfo -> existingContactInfo.getId().equals(newContactInfo.getId()))
                    .forEach(existingContactInfo -> {
                        existingContactInfo.setContactValue(newContactInfo.getContactValue());
                        contactInfoDao.editContact(existingContactInfo);
                    });
        }
    }

    private void updateEducationInfo(List<EducationInfoDto> newEducationList, User user, Long resumeId) {
        if (newEducationList == null) return;

        List<EducationInfo> existingEducationList = educationInfoDao.getEducationInfoByResumeId(resumeId);

        for (EducationInfoDto newEducation : newEducationList) {
            existingEducationList.stream()
                    .filter(existingEducation -> existingEducation.getId().equals(newEducation.getId()))
                    .forEach(existingEducation -> {
                        long years = ChronoUnit.YEARS.between(newEducation.getStartDate(), newEducation.getEndDate());
                        int yearsAsInt = (int) years;

                        existingEducation.setInstitution(newEducation.getInstitution());
                        existingEducation.setProgram(newEducation.getProgram());
                        existingEducation.setStartDate(newEducation.getStartDate());
                        existingEducation.setEndDate(newEducation.getEndDate());
                        existingEducation.setDegree(newEducation.getDegree());

                        if (newEducation.getStartDate().isAfter(newEducation.getEndDate()) || yearsAsInt > user.getAge()) {
                            log.error("Incorrect date for education");
                            return;
                        }

                        educationInfoDao.editEducation(existingEducation);
                    });
        }
    }

    private void updateWorkExperienceInfo(List<WorkExperienceInfoDto> newWorkExperienceList, User user, Long resumeId) {
        if (newWorkExperienceList == null) return;

        List<WorkExperienceInfo> existingWorkExperienceList = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resumeId);

        for (WorkExperienceInfoDto newWorkExperience : newWorkExperienceList) {
            existingWorkExperienceList.stream()
                    .filter(existingWorkExperience -> existingWorkExperience.getId().equals(newWorkExperience.getId()))
                    .forEach(existingWorkExperience -> {
                        if (newWorkExperience.getYears() > user.getAge() || newWorkExperience.getYears() < 0) {
                            log.error("Work experience years exceed user's age!");
                            return;
                        }

                        existingWorkExperience.setYears(newWorkExperience.getYears());
                        existingWorkExperience.setCompanyName(newWorkExperience.getCompanyName());
                        existingWorkExperience.setPosition(newWorkExperience.getPosition());
                        existingWorkExperience.setResponsibility(newWorkExperience.getResponsibility());

                        workExperienceInfoDao.editWorkExp(existingWorkExperience);
                    });
        }
    }

    @Override
    public void deleteResume(Long applicantId, Long resumeId) {
        Optional<User> userOptional = userDao.getUserById(applicantId);
        if (userOptional.isEmpty()) {
            log.error("There's no user by id " + applicantId);
            return;
        }

        Optional<Resume> resumeOptional = resumeDao.getResumeById(resumeId);
        if (resumeOptional.isEmpty()){
            log.error("There's no resume with id " + resumeId);
            return;
        }

        if (!resumeDao.isResumeDeletable(resumeId)) {
            log.error("There are responded applicants for this resume, cannot delete!");
            return;
        }

        workExperienceInfoDao.deleteWorkExp(resumeId);
        educationInfoDao.deleteEducation(resumeId);
        contactInfoDao.deleteContact(resumeId);

        resumeDao.deleteResume(resumeId);
    }

    public List<Resume> getResumesByApplicant(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return Collections.emptyList();
        }

        String userEmail = authentication.getName();
        return resumeDao.getResumesByApplicant(userEmail);
    }

    public  List<Category> getAllCategories(){
        List<Category> categories = categoryDao.getCategories();
        return categories;
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

    private List<ResumeDto> getResumeDto(List<Resume> foundResumes) {
        List<ResumeDto> dto = new ArrayList<>();
        for (Resume resume : foundResumes) {
            ResumeDto resumeDto = ResumeDto.builder()
                    .id(resume.getId())
                    .applicantId(resume.getApplicantId())
                    .categoryId(resume.getCategoryId())
                    .name(resume.getName())
                    .isActive(resume.getIsActive())
                    .createdDate(resume.getCreatedDate())
                    .updateTime(resume.getUpdateTime())
                    .salary(resume.getSalary())
                    .build();

            List<ContactInfoDto> contactInfoList = contactInfoDao.getContactInfoByResumeId(resume.getId()).stream()
                    .map(this::mapToContactInfoDto)
                    .collect(Collectors.toList());

            List<EducationInfoDto> educationList = educationInfoDao.getEducationInfoByResumeId(resume.getId()).stream()
                    .map(this::mapToEducationInfoDto)
                    .collect(Collectors.toList());

            List<WorkExperienceInfoDto> workExperienceList = workExperienceInfoDao.getWorkExperienceInfoByResumeId(resume.getId()).stream()
                    .map(this::mapToWorkExperienceInfoDto)
                    .collect(Collectors.toList());

            resumeDto.setContactInfo(contactInfoList);
            resumeDto.setEducationList(educationList);
            resumeDto.setWorkExperienceList(workExperienceList);

            dto.add(resumeDto);
        }

        return dto;
    }

    private ContactInfoDto mapToContactInfoDto(ContactInfo contactInfo) {
        return ContactInfoDto.builder()
                .id(contactInfo.getId())
                .typeId(contactInfo.getTypeId())
                .resumeId(contactInfo.getResumeId())
                .contactValue(contactInfo.getContactValue())
                .build();
    }

    private EducationInfoDto mapToEducationInfoDto(EducationInfo educationInfo) {
        return EducationInfoDto.builder()
                .id(educationInfo.getId())
                .resumeId(educationInfo.getResumeId())
                .institution(educationInfo.getInstitution())
                .program(educationInfo.getProgram())
                .startDate(educationInfo.getStartDate())
                .endDate(educationInfo.getEndDate())
                .degree(educationInfo.getDegree())
                .build();
    }

    private WorkExperienceInfoDto mapToWorkExperienceInfoDto(WorkExperienceInfo workExperienceInfo) {
        return WorkExperienceInfoDto.builder()
                .id(workExperienceInfo.getId())
                .resumeId(workExperienceInfo.getResumeId())
                .years(workExperienceInfo.getYears())
                .companyName(workExperienceInfo.getCompanyName())
                .position(workExperienceInfo.getPosition())
                .responsibility(workExperienceInfo.getResponsibility())
                .build();
    }

    private WorkExperienceInfo makeWorkExperienceInfo(WorkExperienceInfoDto workExperienceInfoDto) {
        return WorkExperienceInfo.builder()
                .id(workExperienceInfoDto.getId())
                .resumeId(workExperienceInfoDto.getResumeId())
                .years(workExperienceInfoDto.getYears())
                .companyName(workExperienceInfoDto.getCompanyName())
                .position(workExperienceInfoDto.getPosition())
                .responsibility(workExperienceInfoDto.getResponsibility())
                .build();
    }

    private EducationInfo makeEducationInfo(EducationInfoDto educationInfoDto){
        return EducationInfo.builder()
                .id(educationInfoDto.getId())
                .resumeId(educationInfoDto.getResumeId())
                .institution(educationInfoDto.getInstitution())
                .program(educationInfoDto.getProgram())
                .startDate(educationInfoDto.getStartDate())
                .endDate(educationInfoDto.getEndDate())
                .degree(educationInfoDto.getDegree())
                .build();
    }

    private ContactInfo makeContactInfo(ContactInfoDto contactInfoDto){
        return ContactInfo.builder()
                .id(contactInfoDto.getId())
                .typeId(contactInfoDto.getTypeId())
                .resumeId(contactInfoDto.getResumeId())
                .contactValue(contactInfoDto.getContactValue())
                .build();
    }
}
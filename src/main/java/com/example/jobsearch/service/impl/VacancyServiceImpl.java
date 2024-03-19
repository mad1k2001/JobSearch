package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.ResumeDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.VacancyDto;
import com.example.jobsearch.enums.AccountType;
import com.example.jobsearch.model.Resume;
import com.example.jobsearch.model.User;
import com.example.jobsearch.model.Vacancy;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private User user;
    @Override
    public List<VacancyDto> getVacancies() {
        if (user.getAccountType() != AccountType.APPLICANT) {
            log.error("Only applicants can view vacancies.");
        }
        List<Vacancy> vacancies = vacancyDao.getVacancies();
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(mapToDto(e)));
        return dtos;
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(Long categoryId) {
        if (user.getAccountType() != AccountType.APPLICANT) {
            log.error("Only applicants can view vacancies.");
        }
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(categoryId);
        List<VacancyDto> dtos = new ArrayList<>();
        vacancies.forEach(e -> dtos.add(mapToDto(e)));
        return dtos;
    }

    @Override
    public Long addVacancy(VacancyDto vacancyDto){
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can add vacancies.");
        }
        Vacancy vacancy = new Vacancy();
        vacancy.setId(vacancyDto.getId());
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setCategoryId(vacancyDto.getCategoryId());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setIsActivate(vacancyDto.getIsActivate());
        vacancy.setCreatedDate(vacancyDto.getCreatedDate());
        vacancy.setUpdateTime(vacancyDto.getUpdateTime());
        return vacancyDao.addVacancy(vacancy);
    }

    @Override
    public void editVacancy(VacancyDto vacancyDto) {
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can edit vacancies.");
        }
        Vacancy vacancy = new Vacancy();
        vacancy.setId(vacancyDto.getId());
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());
        vacancy.setCategoryId(vacancyDto.getCategoryId());
        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setIsActivate(vacancyDto.getIsActivate());
        vacancy.setCreatedDate(vacancyDto.getCreatedDate());
        vacancy.setUpdateTime(vacancyDto.getUpdateTime());
        vacancyDao.editVacancy(vacancy);
    }

    @Override
    public void deleteVacancy(Long id) {
        if (user.getAccountType() != AccountType.EMPLOYER) {
            log.error("Only employers can delete vacancies.");
        }
        vacancyDao.deleteVacancy(id);
    }

    private VacancyDto mapToDto(Vacancy vacancy) {
        return VacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategoryId())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .isActivate(vacancy.getIsActivate())
                .authorId(vacancy.getAuthorId())
                .createdDate(vacancy.getCreatedDate())
                .updateTime(vacancy.getUpdateTime())
                .build();
    }
}

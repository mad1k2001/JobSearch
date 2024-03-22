package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.RespondedApplicationDao;
import com.example.jobsearch.dao.ResumeDao;
import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.*;
import com.example.jobsearch.enums.AccountType;
import com.example.jobsearch.exeptions.ResumeNotFoundException;
import com.example.jobsearch.exeptions.VacancyNotFoundException;
import com.example.jobsearch.model.*;
import com.example.jobsearch.service.VacancyService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;
    private final UserDao userDao;
    private final ResumeDao resumeDao;
    private final RespondedApplicationDao respondedApplicationDao;
    @Override
    public List<VacancyDto> getVacancies() {
        List<Vacancy> vacancies = vacancyDao.getVacancies();
        return getVacancyDto(vacancies);
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(Long categoryId) {
        List<Vacancy> vacancies = vacancyDao.getVacanciesByCategory(categoryId);
        return getVacancyDto(vacancies);
    }

    @Override
    public Optional<VacancyDto> getVacancyById(Long id) throws VacancyNotFoundException {
        return vacancyDao.getVacancyById(id)
                .map(this::mapToDto)
                .map(Optional::of)
                .orElseThrow(() -> new ValidationException("Can't find vacancy with id: " + id));
    }

    @Override
    public Long addVacancy(VacancyDto vacancyDto, Long authorId) {
        AccountType accountType = userDao.getUserAccountTypeById(authorId);

        Optional<User> userOptional = userDao.getUserById(authorId);
        if (userOptional.isEmpty()) {
            log.error("Can't find user with id: " + authorId);
            return null;
        }
        if (accountType != AccountType.EMPLOYER) {
            log.error("User with id " + authorId + " is not an employer and cannot create a vacancy.");
            return null;
        }

        if (vacancyDto.getExpFrom() < 0 || vacancyDto.getExpFrom() > 60) {
            log.error("Impossible case. Can't create vacancy with exp. from " + vacancyDto.getExpFrom());
            return null;
        }

        if (vacancyDto.getExpTo() < vacancyDto.getExpFrom() || vacancyDto.getExpTo() > 60) {
            log.error("Impossible case. Can't create vacancy with exp. to " + vacancyDto.getExpTo());
            return null;
        }

        Vacancy vacancy = makeVacancy(vacancyDto);
        return vacancyDao.addVacancy(vacancy);
    }


    @Override
    public void editVacancy(Long authorId, Long vacancyId, VacancyDto vacancyDto) {
        AccountType accountType = userDao.getUserAccountTypeById(authorId);
        Optional<User> userOptional = userDao.getUserById(authorId);
        if (userOptional.isEmpty()) {
            log.error("Can't find user with id: " + authorId);
        }
        if (accountType != AccountType.EMPLOYER) {
            log.error("User with id " + authorId + " is not an employer and cannot create a vacancy.");
        }
        if (!userDao.getUserById(authorId).isPresent()){
            log.error("No user with id " +vacancyId);
        }
        Vacancy vacancy = makeVacancy(vacancyDto);
        vacancyDao.editVacancy(vacancy);
    }

    @Override
    public void deleteVacancy(Long authorId, Long vacancyId) {
        Optional<User> userOptional = userDao.getUserById(authorId);
        if (userOptional.isEmpty()) {
            log.error("Can't find user with id: " + authorId);
        }
        AccountType accountType = userDao.getUserAccountTypeById(authorId);
        if (accountType != AccountType.EMPLOYER) {
            log.error("User with id " + authorId + " is not an employer and cannot create a vacancy.");
        }
        if (!vacancyDao.deletable(vacancyId)){
            log.error("Can't delete this vacancy ");
        }
        if (!vacancyDao.getVacancyById(vacancyId).isPresent()){
            log.error("No vacancy with id "+vacancyId);
        }
        vacancyDao.deleteVacancy(authorId);
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

    private Vacancy makeVacancy(VacancyDto vacancyDto){
        return Vacancy.builder()
                .id(vacancyDto.getId())
                .name(vacancyDto.getName())
                .description(vacancyDto.getDescription())
                .categoryId(vacancyDto.getCategoryId())
                .salary(vacancyDto.getSalary())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .isActivate(vacancyDto.getIsActivate())
                .authorId(vacancyDto.getAuthorId())
                .createdDate(vacancyDto.getCreatedDate())
                .updateTime(vacancyDto.getUpdateTime())
                .build();
    }

    private List<VacancyDto> getVacancyDto(List<Vacancy> vacancies) {
        List<VacancyDto> dto = new ArrayList<>();
        vacancies.forEach(e -> dto.add( VacancyDto.builder()
                .id(e.getId())
                .name(e.getName())
                .authorId(e.getAuthorId())
                .categoryId(e.getCategoryId())
                .description(e.getDescription())
                .salary(e.getSalary())
                .expTo(e.getExpTo())
                .expFrom(e.getExpFrom())
                .createdDate(e.getCreatedDate())
                .updateTime(e.getUpdateTime())
                .isActivate(e.getIsActivate())
                .build()));
        return dto;
    }
}

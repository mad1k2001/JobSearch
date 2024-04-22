package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.RespondedApplicationDao;
import com.example.jobsearch.dao.ResumeDao;
import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.*;
import com.example.jobsearch.model.*;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
    public Optional<VacancyDto> getVacancyById(Long id) throws NoSuchElementException {
        return vacancyDao.getVacancyById(id)
                .map(this::mapToDto)
                .map(Optional::of)
                .orElseThrow(() -> new NoSuchElementException("Can't find vacancy with id: " + id));
    }

    @Override
    public Long addVacancy(VacancyDto vacancyDto, Long authorId) {
        Optional<User> userOptional = userDao.getUserById(authorId);
        if (userOptional.isEmpty()) {
            log.error("Can't find user with id: " + authorId);
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

        vacancyDto.setAuthorId(authorId);
        Vacancy vacancy = makeVacancy(vacancyDto);
        return vacancyDao.addVacancy(vacancy);
    }


    @Override
    public void editVacancy(Long authorId, Long vacancyId, VacancyDto vacancyDto) {
        Optional<User> userOptional = userDao.getUserById(authorId);
        if (userOptional.isEmpty()) {
            log.error("Can't find user with id: " + authorId);
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

        if (!vacancyDao.isVacancyDeletable(vacancyId)){
            log.error("Can't delete this vacancy ");
        }
        if (!vacancyDao.getVacancyById(vacancyId).isPresent()){
            log.error("No vacancy with id "+vacancyId);
        }
        vacancyDao.deleteVacancy(authorId);
    }

    public List<Vacancy> getVacanciesByEmployer(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return Collections.emptyList();
        }

        String userEmail = authentication.getName();
        return vacancyDao.getVacanciesByEmployer(userEmail);
    }

    public Page<VacancyDto> getActiveVacancies(int pageNumber) {
        List<VacancyDto> vacancies = getVacancyDto(vacancyDao.getActiveVacancies());
        return toPage(vacancies, PageRequest.of(pageNumber, 5));
    }

    private Page<VacancyDto> toPage(List<VacancyDto> vacancies, Pageable pageable){
        if (pageable.getOffset() >= vacancies.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = (int) ((pageable.getOffset() + pageable.getPageSize() > vacancies.size() ?
                vacancies.size() : pageable.getOffset() + pageable.getPageSize()));
        List<VacancyDto> subList = vacancies.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, vacancies.size());
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
                .isActivate(true)
                .authorId(vacancyDto.getAuthorId())
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
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

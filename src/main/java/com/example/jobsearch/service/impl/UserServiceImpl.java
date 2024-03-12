package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers(){
        List<User> users = userDao.getUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(mapToDo(e)));
        return dtos;
    }

    @Override
    public List<UserDto> getUsersByName(String name){
        List<User> users = userDao.getUsersByName(name);
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(mapToDo(e)));
        return dtos;
    }

    @Override
    public UserDto getUsersByPhoneNumber(String phoneNumber){
        User user = userDao.getUsersByPhoneNumber(phoneNumber);
        return mapToDo(user);
    }

    @Override
    public UserDto getUsersByEmail(String email){
        User user = userDao.getUsersByEmail(email);
        return mapToDo(user);
    }

    @Override
    public List<UserDto> getApplicantsForVacancy(Long vacancyId) {
        List<User> applicants = userDao.getApplicantsForVacancy(vacancyId);
        List<UserDto> dtos = new ArrayList<>();
        applicants.forEach(applicant -> dtos.add(mapToDo(applicant)));
        return dtos;
    }

    private UserDto mapToDo(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .accountType(user.getAccountType())
                .build();
    }
}

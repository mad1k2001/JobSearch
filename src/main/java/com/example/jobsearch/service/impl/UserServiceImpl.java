package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getUsers(){
        List<User> users = userDao.getUsers();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(e -> dtos.add(makeUserDto(e)));
        return dtos;
    }

    @Override
    public ResponseEntity<?> getUsersByEmail(String email){
        Optional<User> mayBeUser = userDao.getUsersByEmail(email);
        if (mayBeUser.isPresent()){
            return ResponseEntity.ok(mayBeUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public List<UserDto> getApplicantsForVacancy(Long vacancyId) {
        List<User> applicants = userDao.getApplicantsForVacancy(vacancyId);
        List<UserDto> dtos = new ArrayList<>();
        applicants.forEach(applicant -> dtos.add(makeUserDto(applicant)));
        return dtos;
    }

    private UserDto makeUserDto(User user){
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

    public ResponseEntity<List<UserDto>> getUsersByParams(String name, String phone){
        if (name!= null && phone != null){
            List<User> users = userDao.getUsersByParams(name, phone);
            return ResponseEntity.ok(users.stream()
                    .map(this::makeUserDto)
                    .toList());
        } else {
            return ResponseEntity.ok(userDao.getUsers().stream()
                    .map(this::makeUserDto)
                    .toList());
        }
    }
}

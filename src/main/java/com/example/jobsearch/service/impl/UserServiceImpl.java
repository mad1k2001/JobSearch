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
    public ResponseEntity<?> getUserByEmail(String email){
        Optional<User> mayBeUser = userDao.getUserByEmail(email);
        if (mayBeUser.isPresent()){
            return ResponseEntity.ok(mayBeUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsersByName(String name) {
        List<User> users = userDao.getUsersByName(name);
        return ResponseEntity.ok(users.stream()
                .map(this::makeUserDto)
                .toList());
    }

    @Override
    public ResponseEntity<List<UserDto>> getUsersByPhoneNumber(String phoneNumber) {
        Optional<User> users = userDao.getUsersByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(users.stream()
                .map(this::makeUserDto)
                .toList());
    }

    @Override
    public Boolean userExistsByEmail(String email) {
        return userDao.userExistsByEmail(email);
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
}

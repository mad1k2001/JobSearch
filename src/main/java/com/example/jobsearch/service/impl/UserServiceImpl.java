//package com.example.jobsearch.service.impl;
//
//import com.example.jobsearch.dao.UserDao;
//import com.example.jobsearch.dto.UserDto;
//import com.example.jobsearch.model.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl {
//    private final UserDao userDao;
//    private List<UserDto> getUsers(){
//        List<User> users = userDao.getUser();
//        List<UserDto> dtos = new ArrayList<>();
//        users.forEach(e -> UserDto.builder()
//                .id(e.getId())
//                .name(e.getName())
//                .surname(e.getSurname())
//                .age(e.getAge())
//                .email(e.getEmail())
//                .password(e.getPassword())
//                .phoneNumber(e.getPhoneNumber())
//                .avatar(e.getAvatar())
//                .accountType(e.getAccountType()));
//        return dtos;
//    }
//}

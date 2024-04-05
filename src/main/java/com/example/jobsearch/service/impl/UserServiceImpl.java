package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.UserProfileDto;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final VacancyDao vacancyDao;

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
    public ResponseEntity<List<UserDto>> getUsersByPhoneNumber(String phoneNumber)  {
        Optional<User> users = userDao.getUsersByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(users.stream()
                .map(this::makeUserDto)
                .toList());
    }

    @Override
    public Boolean userExistsByEmail(String email) {
        return userDao.userExistsByEmail(email);
    }

    @Override
    public Long addUser(UserDto userDto){
        if (userDao.userExistsByEmail(userDto.getEmail())){
            log.error("User with email " + userDto.getEmail() + " already exists");
        }

        if (userDto.getAge() < 18 || userDto.getAge() > 126){
            log.error("Invalid age");
        }
        User user = makeUser(userDto);
        return userDao.addUser(user);
    }

    @Override
    public void editUser(UserDto userDto) {
        if (userDao.getUserById(userDto.getId()).isEmpty()){
            log.error("No user by id " + userDto.getId() + " was found");
        }
        if (userDto.getAge() < 0 || userDto.getAge() > 126) {
            log.error("Impossible age value");
        }

        User user = makeUser(userDto);
        userDao.editUser(user);
    }

    @Override
    public List<UserDto> getApplicantsByAccountType(String accountType) {
        List<User> applicants = userDao.getUsersByAccountType(accountType);
        List<UserDto> applicantDtos = new ArrayList<>();
        applicants.forEach(applicant -> applicantDtos.add(makeUserDto(applicant)));
        return applicantDtos;
    }

    @Override
    public List<UserDto> getEmployersByAccountType(String accountType) {
        List<User> employers = userDao.getEmployersByAccountType(accountType);
        List<UserDto> employerDtos = new ArrayList<>();
        employers.forEach(employer -> employerDtos.add(makeUserDto(employer)));
        return employerDtos;
    }

    @Override
    public UserProfileDto getUser(Authentication authentication) {
        User user = userDao.getUserByEmail(authentication.getName()).get();
        String accountType = "Employer";
        if (user.getAccountType()==4){
            accountType = "Applicant";
        }

        return UserProfileDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .password(user.getPassword())
                .accountType(accountType)
                .avatar(user.getAvatar())
                .surName(user.getSurname())
                .name(user.getName())
                .build();
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
                .enabled(user.getEnabled())
                .accountType(user.getAccountType())
                .build();
    }

    private User makeUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .avatar(userDto.getAvatar())
                .enabled(userDto.getEnabled())
                .accountType(userDto.getAccountType())
                .build();
    }
}

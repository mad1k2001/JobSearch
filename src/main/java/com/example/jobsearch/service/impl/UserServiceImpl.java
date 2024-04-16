package com.example.jobsearch.service.impl;

import com.example.jobsearch.config.AppConfig;
import com.example.jobsearch.dao.ResumeDao;
import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dao.VacancyDao;
import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.dto.UserDto;
import com.example.jobsearch.dto.UserProfileDto;
import com.example.jobsearch.exeptions.ForbiddenException;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final FileUtil fileUtil;
    private final VacancyDao vacancyDao;
    private final ResumeDao resumeDao;
    private final AppConfig appConfig;


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
    public void editUser(UserDto userDto, Long id, ImageDto imageDto, Authentication auth) {
        User userAuth = userDao.getUserByEmail(auth.getName()).get();
        if (!id.equals(userAuth.getId())) {
            throw new ForbiddenException("Can't change profile because this profile doesn't belong to User");
        }
        if (userDao.getUserById(id).isEmpty()){
            try {
                throw new NoSuchFieldException("Can't find user by id " + id);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
        User u = userDao.getUserById(id).get();
        if (userDto.getName() != null){
            u.setName(userDto.getName());
        }
        if (userDto.getSurName() != null){
            u.setSurname(userDto.getSurName());
        }
        if (userDto.getPhoneNumber() != null){
            u.setPhoneNumber(userDto.getPhoneNumber());
        }
        if (userDto.getAge() != null){
            u.setAge(userDto.getAge());
        }
        if (userDto.getAvatar() != null){
            u.setAvatar(userDto.getAvatar());
        }
        if (userDto.getPassword() != null){
            u.setPassword(appConfig.encoder().encode(userDto.getPassword()));
        }
        if (userDto.getEmail() != null){
            if (!userDao.userExistsByEmail(userDto.getEmail())){
                u.setEmail(userDto.getEmail());
            }
            else {
                throw new DuplicateKeyException("Email already exists");
            }
        }
        userDao.editUser(u);
    }

    @Override
    public void upload(ImageDto imageDto, Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            UserDto userDto = (UserDto) authentication.getPrincipal();
            User user = userDao.getUserByEmail(userDto.getName()).get();
            if (user != null) {
                if (imageDto.getAvatar() != null && !imageDto.getAvatar().isEmpty()) {
                    String filename = FileUtil.saveFile(imageDto.getAvatar(), "images");
                    userDto.setAvatar(filename);
                } else {
                    userDto.setAvatar("data/images/default.jpg");
                }
                userDao.save(user);
            }
        }
    }

    @Override
    public UserProfileDto getUser(Authentication authentication) {
        User user = userDao.getUserByEmail(authentication.getName()).get();
        String accountType = "Employer";
        if (user.getAccountType()==2){
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
                .surName(user.getSurname())
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
                .surname(userDto.getSurName())
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
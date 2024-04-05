package com.example.jobsearch.service.impl;

import com.example.jobsearch.dao.UserDao;
import com.example.jobsearch.dto.ImageDto;
import com.example.jobsearch.model.User;
import com.example.jobsearch.service.UserImageService;
import com.example.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserImageServiceImpl implements UserImageService {

    private final UserDao userDao;

    private final FileUtil fileUtil;

    @Override
    public void upload(ImageDto imageDto) {
        String fileName = fileUtil.saveFile(imageDto.getFile(), "images");
        User user = userDao.getUserById(imageDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + imageDto.getUserId()));
        user.setAvatar(fileName);
        userDao.save(user);
    }

}

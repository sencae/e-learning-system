package com.e_learning_system.services;

import com.e_learning_system.dao.UserInfoRepository;
import com.e_learning_system.dao.UserRepository;
import com.e_learning_system.dto.ModelMapperUtil;
import com.e_learning_system.dto.UpdateUserDto;
import com.e_learning_system.dto.UserDto;
import com.e_learning_system.entities.User;
import com.e_learning_system.googleApi.GoogleDriveService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final ModelMapperUtil modelMapperUtil;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository, UserRepository userRepository, ModelMapperUtil modelMapperUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
        this.modelMapperUtil = modelMapperUtil;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String getFileIdFromUrl(String url) {
        return url.substring(url.indexOf('=') + 1, url.indexOf('&'));
    }

    @Transactional
    public boolean deletePageImgUrl(String url) {
        return userInfoRepository.clearPageImgUrl(url) > 0;
    }
    @Transactional
    public boolean setPageImgUrl(String url, Long id) {
        return userInfoRepository.setPageImgUrl(url, id) > 0;
    }

    public UserDto updateUserInfo(Long id, UpdateUserDto userDto) {
        User user = userRepository.getById(id);
        if (userDto.getPassword() == null) {
            userDto.setPassword(user.getPassword());
            modelMapperUtil.map(userDto, user);
            return modelMapperUtil.map(userRepository.save(user), UserDto.class);
        } else {
            userDto.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            modelMapperUtil.map(userDto, user);
            return modelMapperUtil.map(userRepository.save(user), UserDto.class);
        }

    }
}

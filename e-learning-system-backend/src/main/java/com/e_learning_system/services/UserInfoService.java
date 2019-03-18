package com.e_learning_system.services;

import com.e_learning_system.dao.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public String getFileIdFromUrl(String url){
        return url.substring(url.indexOf('=') + 1, url.indexOf('&'));
    }

    @Transactional
    public boolean deletePageImgUrl(String url){
        return userInfoRepository.clearPageImgUrl(url) > 0;
    }
    @Transactional
    public boolean setPageImgUrl(String url, Long id){
        return userInfoRepository.setPageImgUrl(url,id)>0;
    }
}
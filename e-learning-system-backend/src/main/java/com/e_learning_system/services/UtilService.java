package com.e_learning_system.services;

import org.springframework.stereotype.Service;

@Service
public class UtilService {

    public String getFileIdFromUrl(String url) {
        try {
            return url.substring(url.indexOf('=') + 1, url.indexOf('&'));
        }
        catch (Exception ex){
            return null;
        }
    }
}

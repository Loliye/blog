package com.mikufans.blog.application.service;

import com.mikufans.blog.domain.aggregate.user.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAppService {
    /**
     * 用戶登录
     * @param username
     * @param password
     * @return
     */
    public UserEntity login(String username, String password)
    {
        return null;
    }


    public UserEntity queryUserById(Integer uid)
    {
        return null;
    }

    public void updateByUid(UserEntity userEntity)
    {

    }
}

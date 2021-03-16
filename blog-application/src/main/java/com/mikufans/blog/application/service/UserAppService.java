package com.mikufans.blog.application.service;

import com.mikufans.blog.application.convertor.UserConvertor;
import com.mikufans.blog.domain.aggregate.user.UserEntity;
import com.mikufans.blog.infrastructure.common.CommonUtil;
import com.mikufans.blog.infrastructure.common.TipException;
import com.mikufans.blog.infrastructure.repository.user.UserPo;
import com.mikufans.blog.infrastructure.repository.user.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAppService {

    @Resource
    private UserRepository userRepository;

    /**
     * 用戶登录
     *
     * @param username
     * @param password
     * @return
     */
    public UserEntity login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new TipException("用户名和密码不能为空");
        }

        String pwd = CommonUtil.MD5encode(username + password);
        UserPo user = userRepository.getUserInfoByCond(username, pwd);
        if (user == null)
            throw new TipException("用户名或密码错误");
        return UserConvertor.userPo2Entity(user);

    }


    public UserEntity queryUserById(Integer uid) {
        UserPo userInfoById = userRepository.getUserInfoById(uid);
        if (userInfoById != null)
            return UserConvertor.userPo2Entity(userInfoById);
        return null;
    }

    public void updateByUid(UserEntity userEntity) {
        if (userEntity == null || userEntity.getUid() == null)
            throw new TipException("userEntity is null");
        UserPo userPo = UserConvertor.userEntity2Po(userEntity);
        Integer integer = userRepository.updateUserInfo(userPo);
        if (integer != 1)
            throw new TipException("update user by uid and retrun is not one");
    }
}

package com.mikufans.blog.infrastructure.repository.user;

import com.mikufans.blog.domain.aggregate.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {
    @Override
    public Integer updateUserInfo(UserPo userPo) {
        return null;
    }

    @Override
    public UserPo getUserInfoById(Integer uId) {
        return null;
    }

    @Override
    public UserPo getUserInfoByCond(String username, String password) {
        return null;
    }
}

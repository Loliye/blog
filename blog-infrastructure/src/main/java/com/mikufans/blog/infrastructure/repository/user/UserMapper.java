package com.mikufans.blog.infrastructure.repository.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    /**
     * 更改用户信息
     * @param userPo
     * @return
     */
    Integer updateUserInfo(UserPo userPo);

    UserPo getUserInfoById(@Param("uid") Integer uId);

    UserPo getUserInfoByCond(@Param("username")String username,
                             @Param("password")String password);
}

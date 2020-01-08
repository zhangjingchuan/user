package com.mangmangbang.user.repository;

import com.mangmangbang.user.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by zhangjingchuan on 2020/1/8
 */
public interface UserInfoRepository  extends JpaRepository<UserInfo,String> {

    UserInfo findByOpenid(String openid);
}

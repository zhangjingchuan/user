package com.mangmangbang.user.service.impl;

import com.mangmangbang.user.pojo.UserInfo;
import com.mangmangbang.user.repository.UserInfoRepository;
import com.mangmangbang.user.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by zhangjingchuan on 2020/1/8
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByOpenid(String openid) {

        return this.userInfoRepository.findByOpenid(openid);
    }
}

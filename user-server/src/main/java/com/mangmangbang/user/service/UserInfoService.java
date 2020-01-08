package com.mangmangbang.user.service;

import com.mangmangbang.user.pojo.UserInfo;

/**
 * created by zhangjingchuan on 2020/1/8
 */
public interface UserInfoService {

    /**
     * 通过openid查询用户信息
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}

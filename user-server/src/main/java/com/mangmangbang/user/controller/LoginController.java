package com.mangmangbang.user.controller;

import com.mangmangbang.user.constant.CookieConstant;
import com.mangmangbang.user.constant.RedisConstant;
import com.mangmangbang.user.enums.RoleEnum;
import com.mangmangbang.user.pojo.UserInfo;
import com.mangmangbang.user.service.UserInfoService;
import com.mangmangbang.user.utils.CookieUtil;
import com.mangmangbang.user.vo.ResultFormat;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * created by zhangjingchuan on 2020/1/9
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer")
    public ResultFormat buyer(@RequestParam("openid")String openid, HttpServletResponse response){
        //查询数据
        UserInfo userInfo = this.userInfoService.findByOpenid(openid);
        //判断用户身份
        if(userInfo==null){
            return ResultFormat.error("数据不存在,登录失败");
        }

        if(userInfo.getRole()!=RoleEnum.BUYER.getCode()){
            return ResultFormat.error("角色身份不对");
        }

        //设置cookie
        CookieUtil.set(response,CookieConstant.OPENID,openid,CookieConstant.expire);
        return ResultFormat.success(null);
    }

    @GetMapping("/seller")
    public ResultFormat seller(@RequestParam("openid")String openid,
                               HttpServletResponse response,
                               HttpServletRequest request){

        //判断是否已登陆
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie!=null){

            String value = cookie.getValue();
            String redisValue = this.stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, value));
            if(!StringUtils.isEmpty(redisValue)) {
                return ResultFormat.success(null);
            }
        }
        //查询数据
        UserInfo userInfo = this.userInfoService.findByOpenid(openid);
        //判断用户身份
        if(userInfo==null){
            return ResultFormat.error("数据不存在,登录失败");
        }

        if(userInfo.getRole()!=RoleEnum.SELLER.getCode()){
            return ResultFormat.error("角色身份不对");
        }
        //设置redis key=UUID，value = xyz
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        this.stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),
                openid,
                expire,
                TimeUnit.SECONDS);

        //设置cookie
        CookieUtil.set(response,CookieConstant.TOKEN,token,CookieConstant.expire);
        return ResultFormat.success(null);
    }
}

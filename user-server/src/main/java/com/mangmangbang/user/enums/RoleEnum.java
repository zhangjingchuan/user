package com.mangmangbang.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * created by zhangjingchuan on 2020/1/9
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RoleEnum {

    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ;

    private int code;
    private String message;

}

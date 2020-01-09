package com.mangmangbang.user.vo;

import lombok.Data;

/**
 *
 * http请求返回的数据格式封装
 * created by zhangjingchuan on 2019/12/23
 */
@Data
public class ResultFormat {
    public static Integer SUCCESS = 0;
    public static Integer ERROR = 1;

    private Integer code;
    private String msg;
    private Object data;

    public ResultFormat(){

    }
    public ResultFormat(Object data){
        this.code = SUCCESS;
        this.msg = "请求成功";
        this.data = data;
    }

    public ResultFormat(Integer code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultFormat success(Object data){
        return new ResultFormat(data);
    }

    public static ResultFormat error(Object data){
        return new ResultFormat(ERROR,"执行失败",data);
    }

    public static ResultFormat error(Object data,String msg){
        return new ResultFormat(ERROR,msg,data);
    }

    public static ResultFormat error(String msg){
        return new ResultFormat(ERROR,msg,null);
    }
}

package com.example.clock.enums;

import java.io.Serializable;

/**
 * @author : ex_yi.chen
 * @ClassName : AccessBusinessExceptionEnum
 * @Description : 智慧通行异常状态码
 * @Date: 2021-12-02 13:58
 */
public enum SmartProjectBusinessExceptionEnum implements Serializable {

    /**
     * 系统异常
     */
    UNAUTHORIZED(401, "无访问权限,请联系管理员"),
    TIME_OUT(408, "请求超时"),
    SERVER_ERROR(500, "服务器异常"),

    /**
     * 短信服务异常
     */
    SMS_SERVER_ERROR(601, "短信发送失败"),
    SMS_CODE_ERROR(602, "验证码错误"),
    SMS_CODE_EXPIRED(603, "验证码已经过期"),

    /**
     * keycloak系统异常
     */
    KEYCLOAK_NOT_USERID_ERROR(700, "数据请求失败,请刷新页面..."),

    /**
     * 人员相关异常
     */
    VISITOR_EXIST(800, "该人员信息已存在"),
    VISITOR_PHONE_EXIST(800, "该人员手机号已存在"),
    VISITOR_IDCARD_EXIST(805, "该人员身份证已存在"),
    VISITOR_CODE_EXIST(806, "该人员编号已存在"),
    VISITOR_CARDNUMBER_EXIST(807, "该人员卡号已存在"),
    VISITOR_NOT_EXIST(801, "该人员信息不存在"),
    VISITOR_GUEST_EXIST(802, "该信息已存在访客信息中"),
    VISITOR_PENDING_GUEST_EXIST(802, "该信息已存在待审核访客信息中"),
    GUEST_EXIST_VISITOR(803, "该访客已存在人员信息中"),
    GUEST_NO_EXPIRED(804, "该访客还未过期"),
    USER_EXIST(600, "该用户已存在"),
    /**
     * 设备异常
     */
    AUTHORIZATION_EXIST(901, "授权信息已存在"),
    ALIAS_EXIST(900, "设备名称已存在");


    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    SmartProjectBusinessExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static SmartProjectBusinessExceptionEnum getByCode(int code) {
        SmartProjectBusinessExceptionEnum[] es = SmartProjectBusinessExceptionEnum.values();
        for (SmartProjectBusinessExceptionEnum e : es) {
            if (code == e.getCode()) {
                return e;
            }
        }
        return null;
    }
}

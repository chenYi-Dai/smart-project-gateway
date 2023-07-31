package com.example.keycloakdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : ex_yi.chen
 * @ClassName : ChildNodeSerialType
 * @Description : 批量添加子节点编号类型
 * @Date: 2022-08-08 10:44
 */
@Getter
@AllArgsConstructor
public enum ChildNodeSerialType {

    /**
     * serialType 编号类型 serialTypeDesc编号类型描述
     */
    NUMBER_TYPE(0, "数字"),
    LETTER_TYPE(1, "字母"),
    TEXT_TYPE(3, "文本");

    /**
     * 编号类型
     */
    private int serialType;

    /**
     * 编号类型描述
     */
    private String serialTypeDesc;
}

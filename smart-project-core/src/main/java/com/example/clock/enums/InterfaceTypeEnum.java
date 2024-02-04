package com.example.clock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : ex_yi.chen
 * @ClassName : InterfaceTypeEnum
 * @Description :  采集器建档interface枚举
 * @Date: 2022-10-21 17:54
 */
@Getter
@AllArgsConstructor
public enum  InterfaceTypeEnum {
    /**
     *
     */
    MBUS("MBUS", 1),
    RS485("RS485", 1);

    /**
     * 类型 24 2 代表水表
     */
    private String type;

    /**
     * 类型 17 热水表，16冷水表 1:非费控电表 2:费控电表
     */
    private int value;

    /**
     * 获取code
     *
     * @param type 对应type
     * @return 对应code枚举
     */
    public static InterfaceTypeEnum getCode(String type) {
        InterfaceTypeEnum[] es = InterfaceTypeEnum.values();
        for (InterfaceTypeEnum e : es) {
            if (type.equals(e.getType())) {
                return e;
            }
        }
        return InterfaceTypeEnum.MBUS;
    }
}

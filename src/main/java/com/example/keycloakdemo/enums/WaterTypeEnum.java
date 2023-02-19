package com.example.keycloakdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : ex_yi.chen
 * @ClassName : WaterTypeEnum
 * @Description : 水表类型对应枚举
 * @Date: 2022-10-21 17:30
 */
@Getter
@AllArgsConstructor
public enum  WaterTypeEnum {
    /**
     *
     */
    HOT_WATER(24, 17),
    COLD_WATER(2, 16),
    CONTROLLED_METER(3, 1),
    GATEWAY(26, 2);

    /**
     * 类型 24 2 代表水表
     */
    private int type;

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
    public static WaterTypeEnum getCode(int type) {
        WaterTypeEnum[] es = WaterTypeEnum.values();
        for (WaterTypeEnum e : es) {
            if (type == e.getValue()) {
                return e;
            }
        }
        return WaterTypeEnum.HOT_WATER;
    }
}

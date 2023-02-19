package com.example.keycloakdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : ex_yi.chen
 * @ClassName : NetWorkTypeEnum
 * @Description : 通讯方式枚举类型
 * @Date: 2022-09-17 13:18
 */
@Getter
@AllArgsConstructor
public enum NetWorkTypeEnum {

    /**
     *
     */
    ZIGBEE(0, "zigbee"),
    N485(1, "m485"),
    MBUS(2, "mbus"),
    NB(3, "nb-iot"),
    GATEWAY(4, "gateway");

    /**
     * 类型 通讯方式, 0为zigbee，1为485, 2为mbus 3为NB 4为以太网
     */
    private int type;

    /**
     * 类型 通讯方式, 0为zigbee，1为485, 2为mbus 3为NB 4为以太网
     */
    private String value;

    /**
     * 获取code
     *
     * @param type 对应type
     * @return 对应code枚举
     */
    public static NetWorkTypeEnum getCode(int type) {
        NetWorkTypeEnum[] es = NetWorkTypeEnum.values();
        for (NetWorkTypeEnum e : es) {
            if (type == e.getType()) {
                return e;
            }
        }
        return NetWorkTypeEnum.ZIGBEE;
    }
}

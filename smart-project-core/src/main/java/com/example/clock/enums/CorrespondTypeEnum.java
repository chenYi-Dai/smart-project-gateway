package com.example.clock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : ex_yi.chen
 * @ClassName : CorrespondTypeEnum
 * @Description : 入网方式枚举
 * @Date: 2022-11-05 15:23
 */
@Getter
@AllArgsConstructor
public enum  CorrespondTypeEnum {

    /**
     * code - name
     */
    NB_IOT("nb-iot","NB-IOT"),
    ETHER_NET("ethernet","以太网"),
    M_BUS("m-bus","M-Bus"),
    ZIG_BEE("zigbee","Zigbee"),
    WIFE("wifi","WIFI"),
    RS_485("rs-485","RS-485");


    /**
     * code类型
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 获取code
     *
     * @param code 对应type
     * @return 对应code枚举
     */
    public static CorrespondTypeEnum getCode(String code) {
        CorrespondTypeEnum[] es = CorrespondTypeEnum.values();
        for (CorrespondTypeEnum e : es) {
            if (code.equals(e.getCode())) {
                return e;
            }
        }
        return CorrespondTypeEnum.NB_IOT;
    }
}

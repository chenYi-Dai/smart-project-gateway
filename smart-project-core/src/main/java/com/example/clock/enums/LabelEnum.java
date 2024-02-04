package com.example.clock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务标签类型
 *
 * @author LX
 */
@Getter
@AllArgsConstructor
public enum LabelEnum {


    /**
     * code 业务标签
     * name 对应标签名称
     */
    APARTMENT_LABEL(1, "公寓"),
    DORM_LABEL(2, "宿舍"),
    ACCESS_LABEL(3, "智慧通行"),
    OTHER_LABEL(4, "其他");


    /**
     * 业务标签类型
     */
    private int labelCode;
    /**
     * 业务标签名称
     */
    private String labelName;

    /**
     * 获取code
     *
     * @param code 对应code
     * @return 对应code枚举
     */
    public static LabelEnum getByCode(int code) {
        LabelEnum[] es = LabelEnum.values();
        for (LabelEnum e : es) {
            if (code == e.getLabelCode()) {
                return e;
            }
        }
        return OTHER_LABEL;
    }


}

package com.example.keycloakdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * (工程)同步状态 0 未同步 1已同步
 *
 * @author ex_wen.fei
 */
@Getter
@AllArgsConstructor
public enum SyncStatusEnum implements Serializable {

    /**
     * code 业务标签
     * name 对应标签名称
     */
    NOT_SYNC_TOTAL(0, "未同步"),
    SYNC_TOTAL(1, "已同步");


    /**
     * 签类型
     */
    private int code;
    /**
     * 标签名称
     */
    private String name;

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
        return null;
    }

}

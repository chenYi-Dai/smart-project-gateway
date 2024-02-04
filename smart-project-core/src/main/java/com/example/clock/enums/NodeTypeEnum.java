package com.example.clock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 节点类型
 *
 * @author LX
 */
@Getter
@AllArgsConstructor
public enum NodeTypeEnum {

    /**
     * 上方添加节点
     */
    UP_NODE(1, "添加上级节点"),
    /**
     * 下方添加节点
     */
    DOWN_NODE(2, "添加下级节点"),
    /**
     * 添加子节点
     */
    CHILD_NODE(0, "添加子节点");

    /**
     * 添加节点类型 0添加子节点 1添加上级节点 2添加下级节点
     */
    private int type;

    /**
     * 描述 0添加子节点 1添加上级节点 2添加下级节点
     */
    private String desc;

    /**
     * 获取code
     *
     * @param code 对应code
     * @return 对应code枚举
     */
    public static NodeTypeEnum getCode(int code) {
        NodeTypeEnum[] es = NodeTypeEnum.values();
        for (NodeTypeEnum e : es) {
            if (code == e.getType()) {
                return e;
            }
        }
        return null;
    }
}

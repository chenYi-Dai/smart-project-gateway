package com.example.clock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : ex_yi.chen
 * @ClassName : NodeLevelEnum
 * @Description : 公寓宿舍 节点等级枚举类型
 * @Date: 2022-08-08 10:29
 */
@Getter
@AllArgsConstructor
public enum NodeLevelEnum {

    /**
     * 公寓宿舍等级
     */
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);

    /**
     * 公寓宿舍等级
     */
    private int level;

}

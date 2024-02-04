package com.example.clock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ex_wen.fei
 * @date 2022/10/9
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户vo对象", description = "用户Vo对象返回")
public class UserVO {
    @ApiModelProperty("用户id")
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("企业id")
    private Long eid;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("备注")
    private String remark;

}

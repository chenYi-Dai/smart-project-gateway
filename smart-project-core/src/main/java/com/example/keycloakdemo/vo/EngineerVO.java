package com.example.keycloakdemo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : ex_yi.chen
 * @ClassName : PersonnelVO
 * @Description : 项目施工人员返回对象
 * @Date: 2022-08-31 11:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "EngineerVO", description = "项目施工人员返回对象")
public class EngineerVO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "施工人员名称")
    private String name;

    @ApiModelProperty(value = "施工人员手机号码")
    private String phone;

    @ApiModelProperty(value = "施工人员角色id")
    private Long roleId;

    @ApiModelProperty(value = "施工人员角色名称")
    private String roleName;

    @ApiModelProperty(value = "施工人员创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 用于查询企业所有可用施工人员，项目列表下默认返回1
     */
    @ApiModelProperty(value = "存在状态，0不存在，1存在")
    private Integer state;
}

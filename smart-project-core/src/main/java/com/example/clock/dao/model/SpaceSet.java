package com.example.clock.dao.model;

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
 * @ClassName : SpaceSet
 * @Description : 空间集dto
 * @Date: 2022-07-13 18:05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="空间集dto",description="空间集dto象")
public class SpaceSet {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("空间集名称")
    private String name;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("所属企业")
    private String eidName;

    @ApiModelProperty("所属企业")
    private Long eid;

    @ApiModelProperty("登录服务商id")
    private Long serviceId;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("业务标签 1-公寓,2-宿舍,3-智慧通行,4-其他")
    private int label;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}

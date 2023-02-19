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
 * @ClassName : ProjectVO
 * @Description : 项目信息
 * @Date: 2022-08-30 19:20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectVO", description = "项目信息返回对象")
public class ProjectVO {

    @ApiModelProperty(value = "项目id")
    private Long id;

    @ApiModelProperty(value = "所属企业id")
    private Long eid;

    @ApiModelProperty(value = "服务商Id")
    private Long serviceId;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "所属企业")
    private String eidName;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "空间总数")
    private Integer nodeCount;

    @ApiModelProperty(value = "应绑定设备总数")
    private Integer hasBindCount;

    @ApiModelProperty(value = "设备已绑定总数")
    private Integer bindCount;

    @ApiModelProperty(value = "设备已同步总数")
    private Integer syncCount;

}

package com.example.keycloakdemo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectDetailVO
 * @Description : 项目详情返回对象
 * @Date: 2022-08-31 10:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectDetailVO", description = "项目详情返回对象")
public class ProjectDetailVO {

    @ApiModelProperty(value = "项目id")
    private Long id;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目所属企业id")
    private Long eid;

    @ApiModelProperty(value = "项目所属企业id")
    private Long serviceId;

    @ApiModelProperty(value = "项目所属企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "项目地址")
    private String address;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建人电话号码")
    private String phone;

    @ApiModelProperty(value = "空间集信息")
    private List<ProjectSpaceSetVO> spaceSetList;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "空间总数")
    private Integer spaceNodeCount;

    @ApiModelProperty(value = "应绑定设备总数")
    private Integer hasBindCount;

    @ApiModelProperty(value = "设备已绑定总数")
    private Integer bindCount;

    @ApiModelProperty(value = "设备已同步总数")
    private Integer syncCount;

    @ApiModelProperty(value = "设备未同步总数")
    private Integer notSyncCount;

}

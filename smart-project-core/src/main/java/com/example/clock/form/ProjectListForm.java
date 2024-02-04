package com.example.clock.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectListForm
 * @Description : 项目列表接口入参
 * @Date: 2022-08-30 10:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectListForm", description = "项目列表接口入参")
public class ProjectListForm {


    @ApiModelProperty(value = "搜索关键字")
    private String keywords;

    @ApiModelProperty(value = "服务商id")
    private Long serviceId;

    @ApiModelProperty(value = "登录用户ID，APP登录后获取字段")
    private Long userId;

    private int start;

    private int size;
}

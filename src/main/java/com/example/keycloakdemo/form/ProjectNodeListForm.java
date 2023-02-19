package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : ex_wen.fei
 * @ClassName : ProjectNodeListForm
 * @Description : 项目空间列表接口入参
 * @Date: 2022-09-21 10:08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ProjectNodeListForm", description = "项目空间列表接口入参")
public class ProjectNodeListForm {

    @NotNull(message = "服务商id不能为空")
    @ApiModelProperty(value = "服务商id")
    private Long projectId;

    @ApiModelProperty(value = "空间节点id")
    private Long nodeId;

    @ApiModelProperty(value = "搜索关键字")
    private String keywords;


    private List<Long> parentIds;

    private int start;

    private int size;
}

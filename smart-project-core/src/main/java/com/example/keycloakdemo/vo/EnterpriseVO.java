package com.example.keycloakdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : EnterpriseVO
 * @Description : 企业信息返回对象
 * @Date: 2022-07-14 15:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "企业信息返回对象", description = "企业信息返回对象")
public class EnterpriseVO {

    @ApiModelProperty("企业主键id")
    private Long id;

    @ApiModelProperty("企业名称")
    private String name;

    @ApiModelProperty("企业类型(每一种类型对应一种标签)")
    private List<String> systemType;

}

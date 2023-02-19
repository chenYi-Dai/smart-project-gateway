package com.example.keycloakdemo.dao.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : ex_yi.chen
 * @ClassName : ProjectSpaceSyncDto
 * @Description : MQ消息发送对象
 * @Date: 2022-09-21 10:17
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSpaceSyncDto {

    @ApiModelProperty("消息类型 shop:门店")
    String type;

    @ApiModelProperty("请求方式 add:添加 update:修改 delete:删除")
    String method;

    @ApiModelProperty("数据对象  id,eid,name")
    String data;
}

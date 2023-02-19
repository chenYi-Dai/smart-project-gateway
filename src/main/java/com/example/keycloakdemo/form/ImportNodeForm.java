package com.example.keycloakdemo.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : ex_yi.chen
 * @ClassName : ImportNodeForm
 * @Description : 导入空间节点入参
 * @Date: 2022-07-25 17:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "导入空间节点入参", description = "导入空间节点入参")
public class ImportNodeForm {
    @ApiModelProperty("导入文件名称")
    private MultipartFile file;

    @ApiModelProperty("空间集id")
    private Long id;
}

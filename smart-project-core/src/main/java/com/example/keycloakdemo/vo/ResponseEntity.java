package com.example.keycloakdemo.vo;


import com.example.keycloakdemo.util.MdcUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import java.util.Date;

/**
 * Author wy
 * Date 2018/8/20 0020
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "返回值对象", description = "返回值")
public class ResponseEntity<T> {

    private String traceId = MDC.get(MdcUtil.TRACE_ID);;

    @ApiModelProperty("状态码")
    private int code = 200;

    @ApiModelProperty("返回值消息")
    private String message = "操作成功";
    @ApiModelProperty("返回值")
    private T value;

    @ApiModelProperty("操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp = new Date();

    public ResponseEntity(T value) {
        this.value = value;
    }

}

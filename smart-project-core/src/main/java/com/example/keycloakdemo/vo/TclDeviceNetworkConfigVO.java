package com.example.keycloakdemo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author : ex_yi.chen
 * @ClassName : TclDeviceNetworkConfigVO
 * @Description : 鸿鹄设备返回ssid秘钥对象
 * @Date: 2022-07-13 18:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "TclDeviceNetworkConfigVO", description = "鸿鹄设备返回ssid秘钥对象")
public class TclDeviceNetworkConfigVO {

    @ApiModelProperty("设备唯一SSID与设备列表SSID不同")
    private String ssid;

    @ApiModelProperty("设备图标")
    @JsonProperty("device_icon")
    private String deviceIcon;

    @ApiModelProperty("产品名称")
    @JsonProperty("product_name")
    private String productName;

    @ApiModelProperty("设备热点密码")
    private String pwd;

    @ApiModelProperty("设备版本")
    @JsonProperty("device_version")
    private String deviceVersion;

    @ApiModelProperty(" 配网参数 fast:一键配网 wifi:wifi配网 bt_fast:既支持bluetooth又支持fast")
    @JsonProperty("protocol_params")
    private String protocolParams;

    @ApiModelProperty("配王协议类型 xmpp TCLinkV1")
    @JsonProperty("protocol_type")
    private String protocolType;

    @ApiModelProperty("分配编码")
    private String category;

    @ApiModelProperty("配网页面")
    @JsonProperty("manager_url")
    private String managerUrl;

    @ApiModelProperty("创建时间")
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}

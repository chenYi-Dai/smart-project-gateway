package com.example.keycloakdemo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("用户中心信息")
public class UserCenterInfoVo {

    @ApiModelProperty("用户ID")
    private long id;

    @ApiModelProperty("昵称")
    @JsonProperty("nick_name")
    private String nickName;

    @ApiModelProperty("注册时间")
    @JsonProperty("register_time")
    private Date registerTime;

    @ApiModelProperty("绑定手机号")
    @JsonProperty("bind_mobile")
    private String bindMobile;

    @ApiModelProperty("密码是否已设置")
    @JsonProperty("pwd_set")
    private boolean pwdSet;

    @ApiModelProperty("企业名称")
    @JsonProperty("enterprise_name")
    private String enterpriseName;

    @ApiModelProperty("员工姓名")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty("员工角色")
    @JsonProperty("role_name")
    private String roleName;

    @ApiModelProperty("APP对接信息列表")
    @JsonProperty("app_secrets")
    private List<AppSecret> appSecrets;

    @ApiModelProperty("用户类型 企业管理员 0  企业员工 1")
    private Integer type;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ApiModel("API对接信息")
    public static class AppSecret {

        @ApiModelProperty("appId")
        @JsonProperty("app_id")
        private String appId;

        @ApiModelProperty("app密钥")
        @JsonProperty("app_secret")
        private String appSecret;

        @ApiModelProperty("企业名称")
        @JsonProperty("enterprise_name")
        private String enterpriseName;

        @JsonIgnore
        private Long storeId;
    }
}

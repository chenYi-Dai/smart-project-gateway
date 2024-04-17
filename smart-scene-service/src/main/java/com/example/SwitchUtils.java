package com.example;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fusionbank.common.cc.ConfigClient;
import com.fusionbank.common.cc.ConfigClientImpl;
import com.fusionbank.common.cc.ServerMapInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * CC 配置中心中的切换变量
 * 所有开关以 json 格式配置在字段 additional_field 中。
 * 开关取值约定：0 - 开关关闭，1 - 开关打开
 *
 * 注意以下DEV环境测试中 switch 测试
 *
 * [root@VM-86-132-centos /data/apps]# /usr/local/config_agent/bin/test_api_beta -m vb_ceb_switch
 * Conf Param Map : {
 *   "additional_field" : "{"payWaySwitch":0}"
 *   "ip_0" : "0"
 *   "ip_num" : "1"
 *   "l5_cmdid" : "0"
 *   "l5_freeze_timeout" : "0"
 *   "l5_modid" : "0"
 *   "l5_timeout" : "0"
 *   "l5_use" : "0"
 *   "password" : ""
 *   "port" : "0"
 *   "switch" : "0"
 *   "timeout" : "0"
 *   "type" : "5"
 *   "user_name" : ""
 * }
 */

public class SwitchUtils {

    private static final Logger log = LoggerFactory.getLogger(SwitchUtils.class);

    // CC 中的服务别名、地址别名，两个命名一致
    private static final String CC_SERVER_ALIAS = "vb_ceb_switch";

    /**
     * 以下是各种自定义开关名称，约定：0 - 开关关闭，1 - 开关打开
     *
     *   payWaySwitch - 新旧支付开关，支付 ISO20022 项目引入，待生产稳定运行后可去除
     */
    private static final String PAY_WAY_SWITCH = "payWaySwitch";

    private static final ConfigClient configClient = new ConfigClientImpl();

    public static boolean isCebPaywaySwitchOn() {

        ServerMapInfo serverMapInfo = configClient.getServerMap(CC_SERVER_ALIAS);
        String additionalField = serverMapInfo.getAdditionalField();


        System.out.println(JSON.toJSONString(serverMapInfo));

        System.out.println("additionalField: " + additionalField);


        JSONObject switchObj = (JSONObject)JSON.parse(additionalField);
        return switchObj.getBoolean(PAY_WAY_SWITCH);
    }

}

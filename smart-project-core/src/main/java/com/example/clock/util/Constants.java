package com.example.clock.util;

import java.util.regex.Pattern;

public final class Constants {

    /**
     * 节点排序常量
     */
    public static final Integer ORDER_NUM = 99;

    /**
     * 所有字母的总数
     */
    public static final Integer LETTER = 25;

    /**
     * redis建档过期时间
     */
    public static final Long OVER_TIME = 3L;

    public static final String WIFE_NODE_TYPE = "wifi";

    public static final String CONNECTED_NODE_TYPE = "connected";

    /**
     * 所有的建档列表信息
     */
    public static final String RECORD_LIST_KEY = "recordListKey";

    /**
     * 当前请求保存的建档列表数据
     */
    public static final String CURRENT_RECORD_LIST_KEY = "currentRecordListKey:";

    /**
            * 当前请求保存的建档列表数据
     */
    public static final String LOCK_CURRENT_RECORD_LIST_KEY = "lock:";

    public static final String NUMERIC = "NUMERIC";

    /**
     * 获取建档列表
     */
    public static final String ARCHIVES_LIST_KEY = "getArchivesList:";

    public static final String ARCHIVES_KEY = "getArchives";

    /**
     * 接口请求次数限制key
     */
    public final static String ACCESS_LIMIT_KEY = "str:access:limit:";

    public final static String checkTurnOnKey = "powerSwitch";

    /**
     * 本地短时间缓存
     */
    public static final String LOCAL_CACHE_SHORT = "LOCAL_CACHE_SHORT";

    /**
     * 手机号正则
     */
    public static final Pattern MOBILE_PATTERN = Pattern.compile("^1\\d{10}$");

    public static final String rocketmq_project_space_sync_topic = "project-space-sync-topic";

    public static final String DEVICE_REGISTER = "deviceRegister";

}

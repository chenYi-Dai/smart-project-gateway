package com.example.keycloakdemo.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wy
 * @date 2019/10/14 0014
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseParam {
    /**
     * 起始下标
     */
    private Integer start;
    /**
     * 一页大小
     */
    private Integer size;

    /**
     * 管理员账户或企业名称
     */
    private String keywords;

}

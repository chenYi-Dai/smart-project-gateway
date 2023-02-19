package com.example.keycloakdemo.dao.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 石恒
 * @DateTime: 2019/10/16 20:14
 * @qq: 374696376
 * @Description:
 */

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffHouseMapping implements Serializable {

    private long id;
    /**
     * 员工ID
     */
    private Long staffId;
    /**
     * 房间ID
     */
    private Long houseId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTim;

}

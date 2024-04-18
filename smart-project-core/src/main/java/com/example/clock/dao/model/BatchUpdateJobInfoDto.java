package com.example.clock.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 批量更新客户信息记录dto
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchUpdateJobInfoDto {

    //任务ID'
    Long jobId;

    //更新类型: 1-税务信息, 其它类型待定
    Integer updateType;

    //xboss操作员
    String operator;

    //xboss审批员
    String approver;

    //xboss操作时间
    Date operateTime;

    //xboss审批时间
    Date approveTime;

    //更新客户数量
    Integer updCustNo;

    //更新失败数量
    Integer updFailCustNo;

    //原始文件路径
    String originFilePath;

    //结果文件路径
    String resultFilePath;

    //任务状态: 1-已完成, 2-进行中, 3-已中止
    Integer jobStatus;

    //中止原因
    String abortReason;

    //状态 1-正常 2-非正常
    Integer rstatus;

    Date beginTime;

    Date endTime;
    private Date createTime;
    private Date modifyTime;


    public enum UpdateTaskStatusEnum {
        /**
         * 已完成
         */
        TASK_SUCCESS(1),
        /**
         * 进行中
         */
        TASK_EXECUTING(2),
        /**
         * 失败
         */
        TASK_FAIL(3);


        private int value;

        UpdateTaskStatusEnum(int value) {
            this.value = value;
        }

        public int value() {
            return value;
        }
    }
}

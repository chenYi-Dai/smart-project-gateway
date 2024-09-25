package com.example.clock.task;

import com.dtp.core.thread.DtpExecutor;
import com.example.clock.task.config.ThreadPoolExecutorConfig;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@ConditionalOnProperty(name = "corn.task.state" ,havingValue = "mq")
public class CronTaskHandler {

    @Resource
    private List<TaskHandler> taskHandler;

    private DtpExecutor dtpExecutor = ThreadPoolExecutorConfig.getDtpExecutor();

    @Resource
    private ThreadUtils threadUtils;

    //@XxlJob("CronTaskHandler")
    @Scheduled(cron = "0/20 * * * * ? ")
    public void startCDDJob() throws Exception {
        log.info("CronTaskHandler # execute messageTemplateId:{} cron exec!", XxlJobHelper.getJobParam());
        threadUtils.registDynamicPool(dtpExecutor);
        dtpExecutor.execute(()-> taskHandler.stream().forEach(TaskHandler::handler));
    }
}

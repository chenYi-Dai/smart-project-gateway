package com.example.clock.service.imple;

import com.example.clock.dao.model.SpaceNodeInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author : ex_yi.chen
 * @ClassName : ThreadPoolService
 * @Description : 线程池
 * @Date: 2023-02-06 16:54
 */
@Slf4j
public class ThreadPoolServiceRunable implements Runnable {



    private List<SpaceNodeInfo> spaceNodeInfos;

    public ThreadPoolServiceRunable( List<SpaceNodeInfo> spaceNodeInfos) {
        this.spaceNodeInfos = spaceNodeInfos;
    }

    @SneakyThrows
    @Override
    public void run() {

        log.info("ThreadPoolServiceRunable start threadName | {}",Thread.currentThread().getName());
        for(int i=0;i<spaceNodeInfos.size();i++){
            System.out.println("1111");
        }
    }
}

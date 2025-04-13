package com.kuokuor.demo.service;

import com.kuokuor.demo.handler.ProcessWebSocketHandler;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

@Service
public class ProcessService {

    /**
     * 模拟任务执行，从0到100输出进度
     */
    public void executeLongTask() {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(300); // 模拟任务处理时间
                    ProcessWebSocketHandler.sendProcess(i); // 发送进度
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

}

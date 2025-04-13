package com.kuokuor.demo.controller;

import com.kuokuor.demo.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    /**
     * 任务启动
     *
     * @return
     */
    @PostMapping("/start")
    public ResponseEntity<String> startTask() {
        // 通过WebSocket，从0输出到100
        processService.executeLongTask();
        return ResponseEntity.ok("Task Start");
    }

}

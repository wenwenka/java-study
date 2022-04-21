package com.example.springlogtest.service;

import com.example.springlogtest.common.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WriteServiceImpl implements WriteService {
    @Override
    @SystemLog(methoddesc = "打印日志")
    public void write(String data,int num) {
        System.out.println("执行");
    }
}

package com.example.springlogtest;

import com.example.springlogtest.service.WriteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringLogTestApplicationTests {

    @Autowired
    private WriteService writeService;

    @Test
    void contextLoads() {
        log.info("方法执行前");
        writeService.write("123",12);
        log.info("方法执行完毕");
    }

}

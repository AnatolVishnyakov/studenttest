package com.application.studenttest;

import com.application.studenttest.service.ITestingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StudentTestApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(StudentTestApplication.class);
        ITestingService service = context.getBean(ITestingService.class);
        service.checkStudent();
    }
}

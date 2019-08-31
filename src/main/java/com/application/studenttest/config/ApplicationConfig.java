package com.application.studenttest.config;

import com.application.studenttest.dao.IQuestionDao;
import com.application.studenttest.dao.QuestionDaoImpl;
import com.application.studenttest.service.ITestingService;
import com.application.studenttest.service.TestingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class ApplicationConfig {
    @Autowired
    private ApplicationProperties properties;

    @Bean
    IQuestionDao questionDao() {
        return new QuestionDaoImpl(properties.getFileName());
    }

    @Bean
    ITestingService testingService(IQuestionDao questionDao) {
        return new TestingServiceImpl(questionDao);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/localization/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}

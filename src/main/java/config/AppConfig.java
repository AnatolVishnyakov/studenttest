package config;

import dao.IQuestionDao;
import dao.QuestionDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import service.ITestingService;
import service.TestingServiceImpl;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    IQuestionDao questionDao(@Value("${question.file}") String pathCSVFile) {
        return new QuestionDaoImpl(pathCSVFile);
    }

    @Bean
    ITestingService testingService(IQuestionDao questionDao) {
        return new TestingServiceImpl(questionDao);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
}

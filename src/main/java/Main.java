import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ITestingService;

public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ITestingService service = context.getBean(ITestingService.class);
        service.checkStudent();
    }
}
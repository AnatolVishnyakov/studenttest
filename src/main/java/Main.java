import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.TestingService;

public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        TestingService service = context.getBean(TestingService.class);
        service.checkStudent();
    }
}
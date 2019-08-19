package service;

import dao.IQuestionDao;
import domain.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.String.format;

@Service
public class TestingServiceImpl implements ITestingService {
    @Value("${application.locale}")
    private String locale;
    @Autowired
    private MessageSource messageSource;
    private IQuestionDao dao;
    private int numberOfTrueAnswer = 0;

    public TestingServiceImpl(IQuestionDao dao) {
        this.dao = dao;
    }

    public void checkStudent() throws Exception {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println(messageSource.getMessage("label_last_name", null, new Locale(locale)));
            String firstName = in.nextLine();
            System.out.println(messageSource.getMessage("label_first_name", null, new Locale(locale)));
            String lastName = in.nextLine();

            System.out.println(format("%s %s %s", messageSource.getMessage("label_testing_student", null, new Locale(locale)), firstName, lastName));
            List<Questionnaire> records = dao.receiveQuestions();
            for (Questionnaire questionnaire : records) {
                System.out.println(format("\n%s", questionnaire.getQuestion()));
                String[] answers = questionnaire.getAnswers();
                System.out.println(format("%s\n1) %s 2) %s 3) %s\n", messageSource.getMessage("label_answer_type", null, new Locale(locale)),
                        answers[0], answers[1], answers[2]));
                System.out.println(messageSource.getMessage("label_your_answer", null, new Locale(locale)));
                try {
                    int indexAnswer = in.nextInt();
                    if (answers[indexAnswer - 1].equals(questionnaire.getCorrectAnswer())) {
                        numberOfTrueAnswer++;
                    }
                } catch (ArrayIndexOutOfBoundsException exc) {
                    System.out.println("Не корректный ввод.");
                }
            }

            int percentage = (int) (((double) numberOfTrueAnswer / records.size()) * 100);
            System.out.println(messageSource.getMessage("label_result", new Integer[]{numberOfTrueAnswer, records.size(), percentage}, new Locale(locale)));
        } catch (IOException e) {
            throw new Exception("Не удалось обработать файл.", e);
        }
    }
}

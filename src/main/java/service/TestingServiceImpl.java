package service;

import dao.IQuestionDao;
import domain.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

@Service
public class TestingServiceImpl implements ITestingService {
    private IQuestionDao dao;
    private int numberOfTrueAnswer = 0;
    @Autowired
    private MessageSource messageSource;

    public TestingServiceImpl(IQuestionDao dao) {
        this.dao = dao;
    }

    public void checkStudent() throws Exception {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Введите фамилию: ");
            // TODO localization
//            System.out.print(messageSource.getMessage("input.last_name", null, new Locale("ru", "RU")));
            String firstName = in.nextLine();
            System.out.print("Введите имя: ");
            String lastName = in.nextLine();

            System.out.println(format("Тестирование студента: %s %s", firstName, lastName));
            List<Questionnaire> records = dao.receiveQuestions();
            for (Questionnaire questionnaire : records) {
                System.out.println(format("\n%s", questionnaire.getQuestion()));
                String[] answers = questionnaire.getAnswers();
                System.out.println(format("Варианты ответа:\n1) %s 2) %s 3) %s\n", answers[0], answers[1], answers[2]));
                System.out.println("Ваш ответ: ");
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
            System.out.println(format("\nКоличество правильных ответов %s из 5.\nРезультат: %s%%", numberOfTrueAnswer, percentage));
        } catch (IOException e) {
            throw new Exception("Не удалось обработать файл.", e);
        }
    }
}

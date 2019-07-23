package service;

import dao.QuestionDao;
import domain.Questionnaire;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

public class TestingServiceImpl implements TestingService {
    private QuestionDao dao;

    public TestingServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    public void checkStudent() throws Exception {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Введите фамилию: ");
            String firstName = in.nextLine();
            System.out.print("Введите имя: ");
            String lastName = in.nextLine();

            System.out.println(format("Тестирование студента: %s %s", firstName, lastName));
            List<Questionnaire> records = dao.receiveQuestions();
            for (Questionnaire questionnaire : records) {
                System.out.println(questionnaire.getQuestion());
                String[] answers = questionnaire.getAnswers();
                System.out.println(format("Варианты ответа:\na) %s b) %s c) %s\n", answers[0], answers[1], answers[2]));
                System.out.println("Ваш ответ: ");
                in.nextLine();
            }
        } catch (IOException e) {
            throw new Exception("Не удалось обработать файл.", e);
        }
    }
}

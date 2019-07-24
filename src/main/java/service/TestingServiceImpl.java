package service;

import dao.QuestionDao;
import domain.Questionnaire;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

public class TestingServiceImpl implements TestingService {
    private QuestionDao dao;
    private int numberOfTrueAnswer = 0;

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
                System.out.println(format("\n%s", questionnaire.getQuestion()));
                String[] answers = questionnaire.getAnswers();
                System.out.println(format("Варианты ответа:\n1) %s 2) %s 3) %s\n", answers[0], answers[1], answers[2]));
                System.out.println("Ваш ответ: ");
                int indexAnswer = in.nextInt();
                if (answers[indexAnswer - 1].equals(questionnaire.getCorrectAnswer())) {
                    numberOfTrueAnswer++;
                }
            }
            System.out.println(format("\nКоличество правильных ответов %s из 5.", numberOfTrueAnswer));
        } catch (IOException e) {
            throw new Exception("Не удалось обработать файл.", e);
        }
    }
}

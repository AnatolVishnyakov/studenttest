package dao;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class QuestionDaoImplTest {
    private final String pathCSVFile = "AnswerTheQuestions.csv";

    @Test
    public void receiveQuestions() throws IOException {
        QuestionDao questionDao = new QuestionDaoImpl(pathCSVFile);
        assertEquals(5, questionDao.receiveQuestions().size());
    }
}

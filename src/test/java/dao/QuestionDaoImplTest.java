package dao;

import com.application.studenttest.dao.IQuestionDao;
import com.application.studenttest.dao.QuestionDaoImpl;
import com.application.studenttest.domain.Questionnaire;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QuestionDaoImplTest {

    @Test
    public void receiveQuestions() throws IOException {
        String pathCSVFile = "AnswerTheQuestions.csv";
        IQuestionDao questionDao = new QuestionDaoImpl(pathCSVFile);
        List<Questionnaire> actual = questionDao.receiveQuestions();
        assertEquals(5, actual.size());
    }
}

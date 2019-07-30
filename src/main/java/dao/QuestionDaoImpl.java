package dao;

import domain.Questionnaire;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class QuestionDaoImpl implements IQuestionDao {
    private static final String SEPARATOR = ";";
    private final File inputCSVFile;

    public QuestionDaoImpl(String pathCSVFile) {
        this.inputCSVFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(pathCSVFile)).getFile());
    }

    public List<Questionnaire> receiveQuestions() throws IOException {
        List<Questionnaire> records = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputCSVFile))) {
            String record = bufferedReader.readLine(); // skip header
            while ((record = bufferedReader.readLine()) != null) {
                String[] data = record.split(SEPARATOR);
                String question = data[0];
                String[] answers = Arrays.copyOfRange(data, 1, 4);
                String correctAnswer = data[4];
                records.add(new Questionnaire(question, answers, correctAnswer));
            }
        }
        return records;
    }
}

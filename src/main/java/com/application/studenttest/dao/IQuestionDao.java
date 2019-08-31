package com.application.studenttest.dao;

import com.application.studenttest.domain.Questionnaire;

import java.io.IOException;
import java.util.List;

public interface IQuestionDao {
    List<Questionnaire> receiveQuestions() throws IOException;
}

package domain;

public class Questionnaire {
    // вопрос
    private String question;
    // ответы
    private String[] answers;
    // правильный ответ
    private String correctAnswer;

    public Questionnaire(String question, String[] answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

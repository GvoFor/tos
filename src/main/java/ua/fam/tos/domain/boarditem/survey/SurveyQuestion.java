package ua.fam.tos.domain.boarditem.survey;
import java.util.Map;
import java.util.HashMap;

public class SurveyQuestion {
    private String questionText;
    private Map<String, String> userAnswers;

    public SurveyQuestion() {
        this.userAnswers = new HashMap<>();
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setUserAnswer(String username, String answer) {
        userAnswers.put(username, answer);
    }

    public void setUserAnswers(Map<String, String> userAnswers) {
        this.userAnswers = userAnswers;
    }
    public Map<String, String> getUserAnswers() {
        return userAnswers;
    }
}

package ua.fam.tos.domain.boarditem.survey;
import java.util.Map;
import java.util.HashMap;

public class SurveyQuestion {
    final private String questionText;
    final private Map<String, String> userAnswers;

    public SurveyQuestion(String questionText) {
        this.questionText = questionText;
        this.userAnswers = new HashMap<>();
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setUserAnswer(String username, String answer) {
        userAnswers.put(username, answer);
    }

    public Map<String, String> getUserAnswers() {
        return userAnswers;
    }
}

package ua.fam.tos.dto;

import ua.fam.tos.domain.boarditem.survey.SurveyQuestion;

import java.util.Map;

public class SurveyQuestionDTO {
    private String questionText;
    private Map<String, String> userAnswers;

    public SurveyQuestionDTO(SurveyQuestion question) {
        this.questionText = question.getQuestionText();
        this.userAnswers = question.getUserAnswers();
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Map<String, String> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(Map<String, String> userAnswers) {
        this.userAnswers = userAnswers;
    }
}

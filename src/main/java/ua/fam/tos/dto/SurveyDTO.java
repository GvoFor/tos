package ua.fam.tos.dto;

import ua.fam.tos.domain.boarditem.survey.Survey;
import ua.fam.tos.domain.boarditem.survey.SurveyStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SurveyDTO {
    private List<SurveyQuestionDTO> questions;
    private Map<String, SurveyStatus> submissionStatuses;

    public SurveyDTO(Survey survey) {
        this.questions = survey.getQuestions().stream()
                .map(SurveyQuestionDTO::new)
                .collect(Collectors.toList());
        this.submissionStatuses = survey.getSubmissionStatuses();
    }

    public List<SurveyQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SurveyQuestionDTO> questions) {
        this.questions = questions;
    }

    public Map<String, SurveyStatus> getSubmissionStatuses() {
        return submissionStatuses;
    }

    public void setSubmissionStatuses(Map<String, SurveyStatus> submissionStatuses) {
        this.submissionStatuses = submissionStatuses;
    }

    public boolean isSubmittedFor(String username) {
        return submissionStatuses.get(username) == SurveyStatus.SUBMITTED;
    }

    public boolean isNotSubmittedFor(String username) {
        return submissionStatuses.get(username) == SurveyStatus.UNSUBMITTED;
    }
}

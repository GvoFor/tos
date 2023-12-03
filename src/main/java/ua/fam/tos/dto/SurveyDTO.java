package ua.fam.tos.dto;

import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.survey.Survey;
import ua.fam.tos.domain.boarditem.survey.SurveyStatus;

import java.util.*;
import java.util.stream.Collectors;

public class SurveyDTO {
    private long id;
    private String title;
    private Date publicationTime;
    private List<SurveyQuestionDTO> questions;
    private Map<String, SurveyStatus> submissionStatuses;
    private String creatorUsername;

    public SurveyDTO() {
        questions = new ArrayList<>();
        submissionStatuses = new HashMap<>();
    }

    public SurveyDTO(Survey survey) {
        this.questions = survey.getQuestions().stream()
                .map(SurveyQuestionDTO::new)
                .collect(Collectors.toList());
        this.submissionStatuses = survey.getSubmissionStatuses();
        this.creatorUsername = survey.getCreator().getUsername();
        this.title = survey.getTitle();
        this.publicationTime = survey.getPublicationTime();
        this.id = survey.getId();
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

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Date publicationTime) {
        this.publicationTime = publicationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
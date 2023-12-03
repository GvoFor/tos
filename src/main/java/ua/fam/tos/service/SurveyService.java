package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import ua.fam.tos.domain.boarditem.survey.Survey;
import ua.fam.tos.domain.boarditem.survey.SurveyQuestion;
import ua.fam.tos.dto.SurveyDTO;
import ua.fam.tos.repository.BoardItemRepository;
import ua.fam.tos.repository.ContributorRepository;

import java.util.Date;

@Service
public class SurveyService {

    private final ContributorRepository contributorRepository;
    private final BoardItemRepository boardItemRepository;

    public SurveyService(ContributorRepository contributorRepository, BoardItemRepository boardItemRepository) {
        this.contributorRepository = contributorRepository;
        this.boardItemRepository = boardItemRepository;
    }

    public long save(SurveyDTO dto, long boardId) {
        Survey survey = new Survey();
        survey.setCreator(contributorRepository.findContributorByUsername(dto.getCreatorUsername()).get());
        survey.setTitle(dto.getTitle());
        survey.setSubmissionStatuses(dto.getSubmissionStatuses());
        survey.setQuestions(dto.getQuestions().stream()
                .map(surveyQuestionDTO -> {
                    SurveyQuestion question = new SurveyQuestion();
                    question.setQuestionText(surveyQuestionDTO.getQuestionText());
                    question.setUserAnswers(surveyQuestionDTO.getUserAnswers());
                    return question;
                })
                .toList());
        survey.setPublicationTime(new Date());
        return boardItemRepository.save(survey, boardId);
    }

}

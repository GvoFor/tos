package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import ua.fam.tos.domain.boarditem.BoardItem;
import ua.fam.tos.domain.boarditem.survey.Survey;
import ua.fam.tos.domain.boarditem.survey.SurveyQuestion;
import ua.fam.tos.domain.boarditem.survey.SurveyStatus;
import ua.fam.tos.dto.SurveyDTO;
import ua.fam.tos.repository.BoardItemRepository;
import ua.fam.tos.repository.BoardRepository;
import ua.fam.tos.repository.ContributorRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurveyService {

    private final ContributorRepository contributorRepository;
    private final BoardItemRepository boardItemRepository;
    private final BoardRepository boardRepository;

    public SurveyService(ContributorRepository contributorRepository, BoardItemRepository boardItemRepository, BoardRepository boardRepository) {
        this.contributorRepository = contributorRepository;
        this.boardItemRepository = boardItemRepository;
        this.boardRepository = boardRepository;
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
                .collect(Collectors.toList()));
        return save(survey, boardId);
    }

    public long save(Survey survey, long boardId) {
        survey.setPublicationTime(new Date());
        Map<String, SurveyStatus> statuses = survey.getSubmissionStatuses();
        boardRepository.findById(boardId).get().getAllContributors().forEach(contributor -> {
            statuses.put(contributor.getUsername(), SurveyStatus.UNSUBMITTED);
        });
        return boardItemRepository.save(survey, boardId);
    }

    public void updateAnswers(List<String> answers, String username, long boardId, long surveyId) {
        Optional<Survey> toBeUpdatedOptional = findById(boardId, surveyId);
        Survey toBeUpdated = toBeUpdatedOptional.get();
        for (int i = 0; i < answers.size(); i++) {
            toBeUpdated.getQuestions().get(i).setUserAnswer(username, answers.get(i));
        }
    }

    public Optional<Survey> findById(long boardId, long surveyId) {
        Optional<BoardItem> result = boardItemRepository.findById(boardId, surveyId);
        return result.map(boardItem -> (Survey) boardItem);
    }

}

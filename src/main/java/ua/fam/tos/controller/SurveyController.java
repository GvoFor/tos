package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.fam.tos.domain.Board;
import ua.fam.tos.domain.boarditem.survey.Survey;
import ua.fam.tos.dto.SurveyDTO;
import ua.fam.tos.dto.SurveyQuestionDTO;
import ua.fam.tos.service.SurveyService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/boards/{boardId}/items")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService assignmentService) {
        this.surveyService = assignmentService;
    }

    @PostMapping("/survey/save")
    public String save(@PathVariable long boardId,
                       @ModelAttribute("survey") SurveyDTO survey) {
        long savedId = surveyService.save(survey, boardId);
        return "redirect:/boards/" + boardId + "/items/" + savedId;
    }

    @GetMapping("/survey/new")
    public String create(@PathVariable long boardId,
                         @ModelAttribute("survey") SurveyDTO survey,
                         Model model,
                         Principal user) {
        survey.setCreatorUsername(user.getName());
        survey.setId(-1);
        model.addAttribute("boardId", boardId);
        return "surveyEdit";
    }

    @PostMapping("{itemId}/survey/addQuestion")
    public String addQuestion(@PathVariable long boardId,
                              @PathVariable long itemId) {
        Optional<Survey> surveyOptional = surveyService.findById(boardId, itemId);
        if (surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            survey.addQuestion("New question");
            surveyService.save(survey, boardId);
        }

        return "redirect:/boards/" + boardId + "/items/" + itemId + "/edit";
    }

    @PostMapping("{itemId}/survey/deleteQuestion/{questionIndex}")
    public String deleteQuestion(@PathVariable long boardId,
                                 @PathVariable long itemId,
                                 @PathVariable int questionIndex) {
        Optional<Survey> surveyOptional = surveyService.findById(boardId, itemId);
        if (surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            survey.deleteQuestionByIndex(questionIndex);
            surveyService.save(survey, boardId);
        }

        return "redirect:/boards/" + boardId + "/items/" + itemId + "/edit";
    }

    @PostMapping("{itemId}/survey/saveAnswers")
    public String saveAnswers(@PathVariable long boardId,
                              @PathVariable long itemId,
                              Principal user,
                              @ModelAttribute("survey") SurveyDTO survey) {
        List<String> answers = survey.getQuestions()
                .stream()
                .map(question -> question.getUserAnswers().get(user.getName()))
                .collect(Collectors.toList());
        surveyService.updateAnswers(answers, user.getName(), boardId, itemId);
        return "redirect:/boards/" + boardId + "/items/" + itemId;
    }

}

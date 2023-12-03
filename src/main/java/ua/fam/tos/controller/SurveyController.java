package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.fam.tos.dto.SurveyDTO;
import ua.fam.tos.service.SurveyService;

import java.security.Principal;

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

}

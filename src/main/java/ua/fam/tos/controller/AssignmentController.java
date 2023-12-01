package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.fam.tos.dto.AssignmentDTO;
import ua.fam.tos.service.AssignmentService;

import java.security.Principal;

@Controller
@RequestMapping("/boards/{boardId}/items")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping("/assignment/save")
    public String save(@PathVariable long boardId,
                          @ModelAttribute("assignment") AssignmentDTO assignment) {
        if (assignment.getId() == -1) {
            long savedId = assignmentService.save(assignment);
            return "redirect:/boards/" + boardId + "/items/" + savedId;
        }

        assignmentService.update(assignment);
        return "redirect:/boards/" + boardId + "/items/" + assignment.getId();
    }

    @GetMapping("/assignment/new")
    public String create(@PathVariable long boardId,
                         @ModelAttribute("assignment") AssignmentDTO assignment,
                         Model model,
                         Principal user) {
        assignment.setCreatorUsername(user.getName());
        assignment.setId(-1);
        model.addAttribute("boardId", boardId);
        return "assignmentEdit";
    }

}

package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.fam.tos.dto.AssignmentDTO;
import ua.fam.tos.repository.ContributorRepository;
import ua.fam.tos.service.AssignmentService;

import java.security.Principal;

@Controller
@RequestMapping("/boards/{boardId}/items")
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final ContributorRepository contributorRepository;

    public AssignmentController(AssignmentService assignmentService, ContributorRepository contributorRepository) {
        this.assignmentService = assignmentService;
        this.contributorRepository = contributorRepository;
    }

    @PostMapping("/assignment/save")
    public String save(@PathVariable long boardId,
                       @ModelAttribute("assignment") AssignmentDTO assignment) {
        if (contributorRepository.findContributorByUsername(assignment.getApproverUsername()).isEmpty()) {
            System.out.println("Approver does not exist");
            return "assignmentEdit";
        }

        if (contributorRepository.findContributorByUsername(assignment.getExecutorUsername()).isEmpty()) {
            System.out.println("Executor does not exist");
            return "assignmentEdit";
        }

        long savedId = assignmentService.save(assignment, boardId);
        return "redirect:/boards/" + boardId + "/items/" + savedId;
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

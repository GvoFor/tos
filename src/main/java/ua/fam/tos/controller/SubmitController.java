package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.fam.tos.repository.ContributorRepository;
import ua.fam.tos.service.BoardService;

import java.security.Principal;

@Controller
@RequestMapping("/boards/{boardId}/items/{itemId}/submittion")
public class SubmitController {
    private final BoardService boardService;
    private final ContributorRepository contributorRepository;
    public SubmitController(BoardService service, ContributorRepository contributorRepository) {
        this.boardService = service;
        this.contributorRepository = contributorRepository;
    }

    @PostMapping("/submit")
    public String submit(@PathVariable long boardId,
                         @PathVariable long itemId,
                         Principal user,
                         RedirectAttributes redirectAttributes) {
        return "redirect";
    }

    @PostMapping("/cancel")
    public String cancel(@PathVariable long boardId,
                         @PathVariable long itemId,
                         Principal user,
                         RedirectAttributes redirectAttributes) {
        return "redirect";
    }
}



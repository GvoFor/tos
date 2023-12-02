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
@RequestMapping("/boards/{boardId}/items/{itemId}/approving")
public class ApproveController {

    private final BoardService boardService;
    private final ContributorRepository contributorRepository;


    public ApproveController(BoardService service, ContributorRepository contributorRepository) {
        this.boardService = service;
        this.contributorRepository = contributorRepository;
    }

    @PostMapping("/approve")
    public String approve(@PathVariable long boardId,
                          @PathVariable long itemId,
                          Principal user,
                          RedirectAttributes redirectAttributes) {
        return "redirect";
    }

    @PostMapping("/decline")
    public String decline(@PathVariable long boardId,
                          @PathVariable long itemId,
                          Principal user,
                          RedirectAttributes redirectAttributes) {
        return "redirect";
    }

}

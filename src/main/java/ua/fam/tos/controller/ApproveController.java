package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.fam.tos.domain.Board;
import ua.fam.tos.domain.boarditem.Approvable;
import ua.fam.tos.domain.boarditem.BoardItem;
import ua.fam.tos.domain.exceptions.SubmissionException;
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
        Board board = boardService.getBoardById(boardId).get();
        BoardItem item = board.getItemById(itemId).get();

        if (!(item instanceof Approvable approvableItem)) {
            redirectAttributes.addFlashAttribute("error", "Task is not submittable");
            return "redirect:/boards/" + boardId + "/items/" + itemId;
        }

        try {
            approvableItem.approve(contributorRepository.findContributorByUsername(user.getName()).get());
        } catch (SubmissionException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }


        return "redirect:/boards/" + boardId + "/items/" + itemId;
    }

    @PostMapping("/decline")
    public String decline(@PathVariable long boardId,
                          @PathVariable long itemId,
                          Principal user,
                          RedirectAttributes redirectAttributes) {
        Board board = boardService.getBoardById(boardId).get();
        BoardItem item = board.getItemById(itemId).get();

        if (!(item instanceof Approvable approvableItem)) {
            redirectAttributes.addFlashAttribute("error", "Task is not submittable");
            return "redirect:/boards/" + boardId + "/items/" + itemId;
        }

        try {
            approvableItem.decline(contributorRepository.findContributorByUsername(user.getName()).get());
        } catch (SubmissionException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/boards/" + boardId + "/items/" + itemId;
    }

}

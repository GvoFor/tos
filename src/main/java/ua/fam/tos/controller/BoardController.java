package ua.fam.tos.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.fam.tos.domain.Board;
import ua.fam.tos.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllBoards(Model model, Principal user) {
        model.addAttribute("boards", service.getAllBoardsByUsername(user.getName()));
        return "boards";
    }


    @GetMapping("/{id}/items")
    public String showBoard(Model model, Principal user, @PathVariable long id) {
        Optional<Board> boardOptional = service.getBoardById(id);
        if (boardOptional.isEmpty()) {
            return "redirect:/boards?error";
        }
        Board board = boardOptional.get();
        if (!board.getAllContributors().stream()
                .anyMatch(contributor -> contributor.getUsername().equals(user.getName()))){
            return "redirect:/boards?error";
        }
        model.addAttribute("board", board);
        return "board";
    }
}

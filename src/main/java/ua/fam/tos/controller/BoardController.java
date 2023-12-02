package ua.fam.tos.controller;

import org.springframework.web.bind.annotation.*;
import ua.fam.tos.domain.Board;
import ua.fam.tos.dto.BoardDTO;
import ua.fam.tos.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
        model.addAttribute("username", user.getName());
        model.addAttribute("boards", service
                .getAllBoardsByUsername(user.getName())
                .stream()
                .map(BoardDTO::new)
                .toList()
        );
        return "boards";
    }

    @GetMapping("/{id}/items")
    public String showBoard(Model model, Principal user, @PathVariable long id) {
        Optional<Board> boardOptional = service.getBoardById(id);
        if (boardOptional.isEmpty()) {
            return "redirect:/boards?error";
        }
        Board board = boardOptional.get();
        if (!board.hasContributorWithUsername(user.getName())){
            return "redirect:/boards?error";
        }
        model.addAttribute("username", user.getName());
        model.addAttribute("board", new BoardDTO(board));
        return "board";
    }

    @PostMapping("{id}/rename")
    public String renameBoard(@RequestParam String newTitle,
                              @PathVariable long id) {
        Optional<Board> boardOptional = service.getBoardById(id);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.setTitle(newTitle);
            service.saveBoard(board);
        }

        return "redirect:/boards/" + id + "/items";
    }
}
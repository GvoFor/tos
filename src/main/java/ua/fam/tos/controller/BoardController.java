package ua.fam.tos.controller;

import org.springframework.web.bind.annotation.*;
import ua.fam.tos.domain.Board;
import ua.fam.tos.domain.Contributor;
import ua.fam.tos.dto.BoardDTO;
import ua.fam.tos.repository.ContributorRepository;
import ua.fam.tos.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService service;
    private final ContributorRepository contributorRepository;

    public BoardController(BoardService service, ContributorRepository contributorRepository) {
        this.service = service;
        this.contributorRepository = contributorRepository;
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

    @PostMapping("{id}/delete")
    public String deleteBoard(@PathVariable long id) {
        service.deleteBoardById(id);
        return "redirect:/boards";
    }

    @PostMapping("/new")
    public String newBoard(Principal user) {
        Optional<Contributor> ownerOptional = contributorRepository.findContributorByUsername(user.getName());
        if (ownerOptional.isEmpty()) {
            System.out.println();
            return "redirect:/boards?error";
        }

        Board board = new Board();
        board.setId(-1);
        board.setTitle("New board");
        board.setOwner(ownerOptional.get());
        long savedId = service.saveBoard(board);
        return "redirect:/boards/" + savedId + "/items";
    }

    @PostMapping("{id}/contributors/new")
    public String addNewContributor(@RequestParam("newContributorName") String contributorName,
                                    @PathVariable long id) {
        String redirectURL = "redirect:/boards/" + id + "/items";
        Optional<Contributor> contributorOptional = contributorRepository.findContributorByUsername(contributorName);
        if (contributorOptional.isEmpty()) {
            System.out.println("Contributor not exist");
            return redirectURL;
        }

        Optional<Board> boardOptional = service.getBoardById(id);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.addContributor(contributorOptional.get());
            service.saveBoard(board);
        }
        return redirectURL;
    }

    @PostMapping("{id}/contributors/delete")
    public String deleteContributor(@RequestParam("contributorName") String contributorName,
                                    @PathVariable long id) {
        Optional<Board> boardOptional = service.getBoardById(id);
        if (boardOptional.isPresent()) {
            Board board = boardOptional.get();
            board.deleteContributorByUsername(contributorName);
            service.saveBoard(board);
        }
        return "redirect:/boards/" + id + "/items";
    }

}
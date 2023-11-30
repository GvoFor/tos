package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.fam.tos.domain.Board;
import ua.fam.tos.domain.boarditem.BoardItem;
import ua.fam.tos.domain.boarditem.assignment.Assignment;
import ua.fam.tos.domain.boarditem.material.Material;
import ua.fam.tos.domain.boarditem.survey.Survey;
import ua.fam.tos.domain.boarditem.todolist.ToDoList;
import ua.fam.tos.dto.AssignmentDTO;
import ua.fam.tos.dto.MaterialDTO;
import ua.fam.tos.dto.SurveyDTO;
import ua.fam.tos.dto.ToDoListDTO;
import ua.fam.tos.service.BoardService;
import ua.fam.tos.service.MaterialService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/boards/{boardId}/items")
public class BoardItemController {

    private final BoardService boardService;

    public BoardItemController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/{itemId}")
    public String showBoardItem(Model model, Principal user,
                            @PathVariable long boardId,
                            @PathVariable long itemId) {
        String errorRedirectPath = "redirect:/boards/" + boardId + "/items?error";

        Optional<Board> boardOptional = boardService.getBoardById(boardId);
        if (boardOptional.isEmpty()) {
            return errorRedirectPath;
        }
        Board board = boardOptional.get();

        Optional<BoardItem> itemOptional = board.getItemById(itemId);
        if (itemOptional.isEmpty()) {
            return errorRedirectPath;
        }
        BoardItem item = itemOptional.get();

        if (!item.isKnowAboutContributorWithUsername(user.getName())) {
            return errorRedirectPath;
        }

        if (item instanceof Material) {
            model.addAttribute("material",
                    MaterialService.getMaterialDTO((Material) item));
            return "material";
        }

        if (item instanceof Assignment) {
            model.addAttribute("assignment",
                    new AssignmentDTO((Assignment) item));
            return "assignment";
        }


        if (item instanceof Survey) {
            model.addAttribute("survey",
                    new SurveyDTO((Survey) item));
            return "survey";
        }


        if (item instanceof ToDoList) {
            model.addAttribute("todolist",
                    new ToDoListDTO((ToDoList) item));
            return "todolist";
        }

        return errorRedirectPath;
    }

}

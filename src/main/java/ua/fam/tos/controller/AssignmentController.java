package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.fam.tos.dto.AssignmentDTO;

@Controller
@RequestMapping("/boards/{boardId}/items/{itemId}")
public class AssignmentController {



    @PostMapping("/save/assignment")
    public String save(@PathVariable long boardId,
                       @PathVariable long itemId,
                       @RequestParam String redirectUrl,
                       @ModelAttribute AssignmentDTO dto) {
        System.out.println("Saving...");
        return "redirect:" + redirectUrl;
    }

}

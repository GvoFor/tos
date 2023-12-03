package ua.fam.tos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.fam.tos.dto.MaterialDTO;
import ua.fam.tos.service.MaterialService;

import java.security.Principal;

@Controller
@RequestMapping("/boards/{boardId}/items/{itemId}")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }


    @PostMapping("/material/save")
    public String save(@PathVariable long boardId,
                       @ModelAttribute("material") MaterialDTO material) {
        if(material.getId()==-1){
            long saveId = materialService.save(material);
            return "redirect:/boards/"+boardId+"/items/" + saveId;
        }
        materialService.update(material);
        return "redirect:/boards/"+boardId+"/items/" + material.getId();
    }

    @GetMapping("/material/new")
    public String create(@PathVariable long boardId,
                         @ModelAttribute("material") MaterialDTO material,
                         Model model,
                         Principal user) {
        material.setCreatorUsername(user.getName());
        material.setId(-1);
        model.addAttribute("boardId",boardId);
        return "materialEdit";
    }


}

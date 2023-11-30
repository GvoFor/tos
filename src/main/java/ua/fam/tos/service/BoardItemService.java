package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import ua.fam.tos.domain.boarditem.assignment.Assignment;
import ua.fam.tos.domain.boarditem.material.Material;
import ua.fam.tos.domain.boarditem.survey.Survey;
import ua.fam.tos.domain.boarditem.todolist.ToDoList;
import ua.fam.tos.dto.AssignmentDTO;
import ua.fam.tos.dto.MaterialDTO;
import ua.fam.tos.dto.SurveyDTO;
import ua.fam.tos.dto.ToDoListDTO;

@Service
public class BoardItemService {

    public AssignmentDTO getAssignmentDTO(Assignment assignment) {
        AssignmentDTO dto = new AssignmentDTO();
        return dto;
    }

    public SurveyDTO getSurveyDTO(Survey assignment) {
        SurveyDTO dto = new SurveyDTO();
        return dto;
    }

    public MaterialDTO getMaterialDTO(Material assignment) {
        MaterialDTO dto = new MaterialDTO();
        return dto;
    }

    public ToDoListDTO getToDoListDTO(ToDoList assignment) {
        ToDoListDTO dto = new ToDoListDTO();
        return dto;
    }

}

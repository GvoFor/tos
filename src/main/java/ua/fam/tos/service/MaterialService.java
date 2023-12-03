package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.Attachment;
import ua.fam.tos.domain.boarditem.assignment.Assignment;
import ua.fam.tos.domain.boarditem.material.Material;
import ua.fam.tos.dto.AssignmentDTO;
import ua.fam.tos.dto.MaterialDTO;
import ua.fam.tos.repository.BoardItemRepository;
import ua.fam.tos.repository.ContributorRepository;

import java.util.Date;
import java.util.stream.Collectors;
@Service
public class MaterialService {
    private final ContributorRepository contributorRepository;
    private final BoardItemRepository boardItemRepository;

    public MaterialService(ContributorRepository contributorRepository, BoardItemRepository boardItemRepository) {
        this.contributorRepository = contributorRepository;
        this.boardItemRepository = boardItemRepository;
    }

    public long save(MaterialDTO dto, long boardId) {
        Material material = new Material();
        material.setCreator(contributorRepository.findContributorByUsername(dto.getCreatorUsername()).get());
        material.setTitle(dto.getTitle());
        material.setText(dto.getText());
        material.setPublicationTime(new Date());
        return boardItemRepository.save(material, boardId);
    }


    public void update(MaterialDTO material) {

    }
}

package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import ua.fam.tos.domain.boarditem.assignment.Assignment;
import ua.fam.tos.dto.AssignmentDTO;
import ua.fam.tos.repository.BoardItemRepository;
import ua.fam.tos.repository.ContributorRepository;

import java.util.Date;
@Service
public class AssignmentService {

    private final ContributorRepository contributorRepository;
    private final BoardItemRepository boardItemRepository;

    public AssignmentService(ContributorRepository contributorRepository, BoardItemRepository boardItemRepository) {
        this.contributorRepository = contributorRepository;
        this.boardItemRepository = boardItemRepository;
    }

    public long save(AssignmentDTO dto, long boardId) {
        Assignment assignment = new Assignment();
        assignment.setCreator(contributorRepository.findContributorByUsername(dto.getCreatorUsername()).get());
        assignment.setApprover(contributorRepository.findContributorByUsername(dto.getApproverUsername()).get());
        assignment.setExecutor(contributorRepository.findContributorByUsername(dto.getExecutorUsername()).get());
        assignment.setTitle(dto.getTitle());
        assignment.setText(dto.getText());
        assignment.setStatus(dto.getStatus());
        assignment.setDeadline(dto.getDeadline());
        assignment.setPublicationTime(new Date());
        return boardItemRepository.save(assignment, boardId);
    }

}

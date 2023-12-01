package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import ua.fam.tos.dto.AssignmentDTO;

@Service
public class AssignmentService {

    public long save(AssignmentDTO dto) {
        System.out.println("Saving new assignment...");
        return 0;
    }

    public void update(AssignmentDTO dto) {
        System.out.println("Saving existing assignment...");
    }

}

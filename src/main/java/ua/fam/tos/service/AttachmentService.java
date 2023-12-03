package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.fam.tos.repository.AttachmentRepository;
import ua.fam.tos.repository.ContributorRepository;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final ContributorRepository contributorRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, ContributorRepository contributorRepository) {
        this.attachmentRepository = attachmentRepository;
        this.contributorRepository = contributorRepository;
    }

    public void deleteById(long boardId, long itemId, long attachmentId) {

    }

    public void upload(long boardId, long itemId, MultipartFile file, String attacherUsername) {

    }

}

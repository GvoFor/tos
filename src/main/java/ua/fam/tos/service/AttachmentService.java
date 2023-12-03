package ua.fam.tos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.fam.tos.domain.boarditem.Attachment;
import ua.fam.tos.repository.AttachmentRepository;
import ua.fam.tos.repository.ContributorRepository;

import java.util.Optional;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final ContributorRepository contributorRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, ContributorRepository contributorRepository) {
        this.attachmentRepository = attachmentRepository;
        this.contributorRepository = contributorRepository;
    }

    public Optional<Attachment> getById(long boardId, long itemId, long attachmentId) {
        return attachmentRepository.findById(boardId, itemId, attachmentId);
    }

    public void deleteById(long boardId, long itemId, long attachmentId) {
        attachmentRepository.deleteById(boardId, itemId, attachmentId);
    }

    public void upload(long boardId, long itemId, MultipartFile file, String attacherUsername) {
        attachmentRepository.save(boardId, itemId, file,
                contributorRepository.findContributorByUsername(attacherUsername).get());
    }

}

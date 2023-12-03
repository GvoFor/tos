package ua.fam.tos.repository;

import org.springframework.web.multipart.MultipartFile;
import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.Attachment;

import java.util.Optional;

public interface AttachmentRepository {

    Optional<Attachment> findById(long boardId, long itemId, long attachmentId);

    void save(long boardId, long itemId, MultipartFile file, Contributor attacher);

    void deleteById(long boardId, long itemId, long attachmentId);

}

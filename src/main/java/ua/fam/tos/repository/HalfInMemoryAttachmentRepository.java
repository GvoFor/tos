package ua.fam.tos.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import ua.fam.tos.domain.Board;
import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.Attachable;
import ua.fam.tos.domain.boarditem.Attachment;
import ua.fam.tos.domain.boarditem.BoardItem;
import ua.fam.tos.domain.exceptions.AttachmentException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Repository
public class HalfInMemoryAttachmentRepository implements AttachmentRepository {

    private long counter;
    private final BoardRepository boardRepository;

    public HalfInMemoryAttachmentRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Optional<Attachment> findById(long boardId, long itemId, long attachmentId) {
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        if (boardOptional.isEmpty()) {
            throw new AttachmentException("Board does not exist");
        }
        Board board = boardOptional.get();

        Optional<BoardItem> itemOptional = board.getItemById(itemId);
        if (itemOptional.isEmpty()) {
            throw new AttachmentException("BoardItem does not exist");
        }
        BoardItem item = itemOptional.get();

        if (!(item instanceof Attachable attachableItem)) {
            throw new AttachmentException("BoardItem haven't attachments support");
        }

        return attachableItem.getAttachmentById(attachmentId);
    }

    @Override
    public void save(long boardId, long itemId, MultipartFile multipartFile, Contributor attacher) {
        boardRepository.findById(boardId).ifPresent(board -> {
            board.getItemById(itemId).ifPresent(item -> {
                if (!(item instanceof Attachable attachableItem)) {
                    throw new AttachmentException("BoardItem haven't attachments support");
                }
                Attachment attachment = new Attachment();
                attachment.setAttacher(attacher);
                attachment.setTitle(multipartFile.getOriginalFilename());
                attachment.setId(counter++);

                try {
                    String sourceDirPath = "data/" + boardId + "/" + itemId;
                    String sourcePath = sourceDirPath + "/" + attachment.getId();
                    attachment.setSourcePath(sourcePath);
                    File dir = new File(sourceDirPath);
                    dir.mkdirs();
                    File file = new File(sourcePath);
                    file.createNewFile();
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        multipartFile.getInputStream().transferTo(fos);
                    }
                } catch (IOException e) {
                    throw new AttachmentException("Unable to save file: " + e.getMessage());
                }

                attachableItem.addAttachment(attachment);
            });
        });
    }

    @Override
    public void deleteById(long boardId, long itemId, long attachmentId) {
        boardRepository.findById(boardId).ifPresent(board -> {
            board.getItemById(itemId).ifPresent(item -> {
                if (!(item instanceof Attachable attachableItem)) {
                    throw new AttachmentException("Task haven't attachments support");
                }
                attachableItem.removeAttachmentById(attachmentId);
            });
        });
    }
}
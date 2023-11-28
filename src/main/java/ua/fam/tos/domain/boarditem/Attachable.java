package ua.fam.tos.domain.boarditem;

import java.util.Optional;

public interface Attachable {
	
	void addAttachment(Attachment attachment);
	Optional<Attachment> getAttachmentById(long id);
	void removeAttachmentById(long id);
	
}
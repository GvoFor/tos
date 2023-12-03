package ua.fam.tos.dto;

import ua.fam.tos.domain.boarditem.Attachment;

public class AttachmentDTO {

    private long id;
    private String title;

    public AttachmentDTO(Attachment attachment) {
        id = attachment.getId();
        title = attachment.getTitle();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

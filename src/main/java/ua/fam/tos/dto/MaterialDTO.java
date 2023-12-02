package ua.fam.tos.dto;

import ua.fam.tos.domain.boarditem.material.Material;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialDTO {

    private long id;
    private String title;
    private Date publicationTime;
    private String creatorUsername;

    public String text;
    public List<AttachmentDTO> attachments;

    public MaterialDTO(){}

    public MaterialDTO(Material material){
        this.id = material.getId();
        this.title = material.getTitle();
        this.publicationTime = material.getPublicationTime();
        this.creatorUsername = material.getCreator().getUsername();
        this.text = material.getText();
        this.attachments = material.getAttachments()
                .stream()
                .map(AttachmentDTO::new)
                .collect(Collectors.toList());
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

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Date publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }


}

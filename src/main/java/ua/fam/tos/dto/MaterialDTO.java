package ua.fam.tos.dto;

import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.Attachment;
import ua.fam.tos.domain.boarditem.material.Material;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialDTO {

    private String title;
    private Date publicationTime;
    private String creatorUsername;

    public String text;
    public List<String> attachmentTitles;

    //NOT SURE
    public List<String> viewerNameList;

    public MaterialDTO(Material material){
        this.title = material.getTitle();
        this.publicationTime = material.getPublicationTime();
        this.creatorUsername = material.getCreator().getUsername();
        this.text = material.getText();
        this.attachmentTitles = material.getAttachments()
                .stream()
                .map(Attachment::getTitle)
                .collect(Collectors.toList());
        this.viewerNameList =material.getViewerList()
                .stream()
                .map(Contributor::getUsername)
                .collect(Collectors.toList());;
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

    public List<String> getAttachmentTitles() {
        return attachmentTitles;
    }

    public void setAttachmentTitles(List<String> attachmentTitles) {
        this.attachmentTitles = attachmentTitles;
    }

    public List<String> getViewerNameList() {
        return viewerNameList;
    }

    public void setViewerNameList(List<String> viewerNameList) {
        this.viewerNameList = viewerNameList;
    }
}

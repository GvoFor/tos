package ua.fam.tos.dto;

import ua.fam.tos.domain.boarditem.BoardItem;

import java.util.Date;

public class BoardItemDTO {

    private long id;
    private String title;
    private Date publicationTime;

    public BoardItemDTO(BoardItem item) {
        id = item.getId();
        title = item.getTitle();
        publicationTime = item.getPublicationTime();
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
}

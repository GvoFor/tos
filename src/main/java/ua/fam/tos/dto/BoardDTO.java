package ua.fam.tos.dto;

import ua.fam.tos.domain.Board;
import ua.fam.tos.domain.Contributor;

import java.util.ArrayList;
import java.util.List;

public class BoardDTO {

    private long id;
    private String title;
    private String ownerName;
    private List<String> contributorNames;
    private List<BoardItemDTO> items;

    public BoardDTO() {
        contributorNames = new ArrayList<>();
    }

    public BoardDTO(Board board) {
        id = board.getId();;
        title = board.getTitle();
        ownerName = board.getOwner().getUsername();
        contributorNames = board.getAllContributors()
                .stream()
                .map(Contributor::getUsername)
                .toList();
        items = board.getAllItems().stream().map(BoardItemDTO::new).toList();
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<String> getContributorNames() {
        return contributorNames;
    }

    public void setContributorNames(List<String> contributorNames) {
        this.contributorNames = contributorNames;
    }

    public List<BoardItemDTO> getItems() {
        return items;
    }

    public void setItems(List<BoardItemDTO> items) {
        this.items = items;
    }
}

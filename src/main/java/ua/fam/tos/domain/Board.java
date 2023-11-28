package ua.fam.tos.domain;

import ua.fam.tos.domain.boarditem.*;

import java.util.*;

public class Board {

    private long id;

    private String title;

    private final List<BoardItem> items;

    private final List<Contributor> contributors;


    public Board(Contributor creator) {
        this();
        contributors.add(creator);
    }

    public Board() {
        id = 0;
        title = "";
        contributors = new ArrayList<>();
        items = new ArrayList<>();
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

    public void addItem(BoardItem item) {
        items.add(item);
    }

    public void deleteItemById(long id) {
        items.removeIf(item -> item.getId() == id);
    }

    public List<BoardItem> getAllItems() {
        return items;
    }

    public Optional<BoardItem> getItemById(long id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findAny();
    }

    public void addContributor(Contributor contributor) {
        if (!contributors.contains(contributor)){
            contributors.add(contributor);
        }
    }

    public void deleteContributorByUsername(String username) {
        contributors.removeIf(contributor -> contributor.getUsername().equals(username));
    }

    public List<Contributor> getAllContributors() {
        return contributors;
    }

    public boolean hasContributorWithUsername(String username) {
        return contributors.stream()
                .anyMatch(contributor -> contributor.getUsername().equals(username));
    }

    public Optional<Contributor> getOwner() {
        if (contributors.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(contributors.get(0));
        }
    }

    public void setOwner(Contributor owner) {
        contributors.remove(owner);
        contributors.add(0,owner);
    }
}
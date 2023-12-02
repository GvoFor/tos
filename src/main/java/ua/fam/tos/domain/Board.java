package ua.fam.tos.domain;

import ua.fam.tos.domain.boarditem.*;

import java.util.*;

public class Board {

    private long id;

    private String title;

    private final List<BoardItem> items;

    private Contributor owner;

    private final Set<Contributor> contributors;

    public Board() {
        id = 0;
        title = "";
        contributors = new HashSet<>();
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
        if (!contributor.equals(owner)) {
            contributors.add(contributor);
        }
    }

    public void deleteContributorByUsername(String username) {
        contributors.removeIf(contributor -> contributor.getUsername().equals(username));
    }

    public List<Contributor> getAllContributors() {
        return contributors.stream().toList();
    }

    public boolean hasContributorWithUsername(String username) {
        return owner.getUsername().equals(username) || contributors.stream()
                .anyMatch(contributor -> contributor.getUsername().equals(username));
    }

    public Contributor getOwner() {
        return owner;
    }

    public void setOwner(Contributor owner) {
        this.owner = owner;
    }
}
package ua.fam.tos.domain;

import java.util.Objects;

public class Contributor {
    private String username;
    private long id;

    public Contributor() {
        this.username = "";
    }
    public Contributor(String username, long id) {
        this.username = username;
        this.id = id;
    }

    public  String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public  long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contributor that = (Contributor) o;
        return getId() == that.getId() && Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getId());
    }
}
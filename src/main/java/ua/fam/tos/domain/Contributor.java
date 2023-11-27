package ua.fam.tos.domain;

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
}



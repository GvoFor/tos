package ua.fam.tos.repository;

import org.springframework.stereotype.Repository;
import ua.fam.tos.domain.Board;
import ua.fam.tos.domain.Contributor;
import ua.fam.tos.domain.boarditem.assignment.Assignment;
import ua.fam.tos.domain.boarditem.material.Material;
import ua.fam.tos.domain.boarditem.survey.Survey;
import ua.fam.tos.domain.boarditem.todolist.ToDo;
import ua.fam.tos.domain.boarditem.todolist.ToDoList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBoardRepository implements BoardRepository {

    private final List<Board> boards;

    public InMemoryBoardRepository() {
        boards = new ArrayList<>();

        Board b = new Board();
        b.setTitle("Board 1 title");
        Assignment a = new Assignment();

        a.setTitle("Assignment 1 title");
        a.setText("Assignment 1 text");
        a.setPublicationTime(new Date());
        a.setDeadline(new Date(System.currentTimeMillis() + 1000 * 120));

        Contributor masha = new Contributor();
        masha.setUsername("masha");

        a.setApprover(masha);
        a.setExecutor(masha);
        a.setCreator(masha);

        b.addItem(a);

        Material m = new Material();
        m.setTitle("Material 1 title");
        m.setText("Material 1 text");
        m.setPublicationTime(new Date());
        m.setId(1);
        b.addItem(m);
        b.setOwner(masha);

        boards.add(b);
        boards.add(b);
        boards.add(b);
        boards.add(b);


        Contributor kate = new Contributor();
        kate.setUsername("kate");
        b = new Board();
        b.setId(1);
        b.setTitle("Kate's first board");
        b.setOwner(kate);
        Survey s1 = new Survey();
        s1.setTitle("Survey title 1");
        s1.setId(0);
        Survey s2 = new Survey();
        s2.setTitle("Survey title 1");
        s2.setId(0);
        b.addItem(s1);
        b.addItem(s2);

        boards.add(b);
    }

    @Override
    public void save(Board board) {
        boards.add(board);
    }

    @Override
    public List<Board> findAll() {
        return List.copyOf(boards);
    }

    @Override
    public Optional<Board> findById(long id) {
        return boards.stream()
                .filter(board -> board.getId() == id)
                .findAny();
    }

    @Override
    public List<Board> findAllByUsername(String username) {
        return boards.stream()
                .filter(board -> board.hasContributorWithUsername(username))
                .toList();
    }
}

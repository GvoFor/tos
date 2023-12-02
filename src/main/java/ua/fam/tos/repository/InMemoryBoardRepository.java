package ua.fam.tos.repository;

import org.springframework.stereotype.Repository;
import ua.fam.tos.domain.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBoardRepository implements BoardRepository {

    private long counter;
    private final List<Board> boards;

    public InMemoryBoardRepository() {
        boards = new ArrayList<>();
    }

    @Override
    public long save(Board board) {
        Optional<Board> existedOptinal = findById(board.getId());
        if (existedOptinal.isPresent()) {
            Board existed = existedOptinal.get();
            existed.setTitle(board.getTitle());
            existed.setOwner(board.getOwner());
            return existed.getId();
        }

        board.setId(counter++);
        boards.add(board);
        return board.getId();
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

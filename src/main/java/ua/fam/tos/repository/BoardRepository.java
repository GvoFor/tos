package ua.fam.tos.repository;

import ua.fam.tos.domain.Board;
import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    long save(Board board);
    List<Board> findAll();
    Optional<Board> findById(long id);
    List<Board> findAllByUsername(String username);

}
